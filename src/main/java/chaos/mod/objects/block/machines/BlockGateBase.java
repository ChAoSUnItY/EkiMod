package chaos.mod.objects.block.machines;

import java.util.List;
import java.util.Random;

import chaos.mod.Eki;
import chaos.mod.objects.block.base.BlockHorizontalBase;
import chaos.mod.objects.item.ItemTicket;
import chaos.mod.objects.item.ItemWrench;
import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.tileentity.TileEntityTicketGate;
import chaos.mod.util.utils.UtilBlockPos;
import chaos.mod.util.utils.UtilStationSystem;
import chaos.mod.util.utils.UtilTranslatable;
import chaos.mod.util.utils.UtilTranslatable.TranslateType;
import chaos.mod.util.utils.UtilTranslatable.UtilTCString;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/***
 * Warning ! This block is missing some logical, to ensure the entire system
 * works fine, you should check every single message from console!
 */
public class BlockGateBase extends BlockHorizontalBase implements ITileEntityProvider {
	public static final AxisAlignedBB GATE_CLOSED_AABB = new AxisAlignedBB(0, 0, 0, 1, 1.5, 1);
	public static final PropertyBool OPEN = PropertyBool.create("open");

	public BlockGateBase(String name) {
		super(name, Eki.STATION, Material.IRON, false);

		setSoundType(SoundType.METAL);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, false));
	}

	@Override
	public int tickRate(World worldIn) {
		return 50;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote)
			if (state.getValue(OPEN)) {
				worldIn.setBlockState(pos, state.withProperty(OPEN, false), 3);
				worldIn.markBlockRangeForRenderUpdate(pos, pos);
				worldIn.playSound(null, pos, SoundEvents.BLOCK_IRON_DOOR_CLOSE, SoundCategory.BLOCKS, 1, 1);
			}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		if (state.getValue(OPEN))
			addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
		else {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, GATE_CLOSED_AABB);
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		ItemStack stack = playerIn.getHeldItem(hand);
		if (!worldIn.isRemote && te instanceof TileEntityTicketGate) {
			TileEntityTicketGate teTG = (TileEntityTicketGate) te;
			BlockPos anchorPos = teTG.getAnchor();
			if (stack.getItem() instanceof ItemTicket) {
				if (stack.hasTagCompound() && stack.getTagCompound().hasKey("value")) {
					NBTTagCompound tag = stack.getTagCompound();
					if (facing == EnumFacing.UP || facing == state.getValue(FACING)) {
						if (tag.hasKey("startPos")) {
							double price = UtilStationSystem.calculatePrice(UtilBlockPos.getPos(tag.getIntArray("startPos")), anchorPos);
							if (tag.getInteger("value") >= price) {
								stack.shrink(1);
								playerIn.sendMessage(new UtilTCString(TranslateType.CHAT, "accessed", anchorPos.getX(), anchorPos.getY(), anchorPos.getZ(), price,
										UtilStationSystem.calculateLength(UtilBlockPos.getPos(tag.getIntArray("startPos")), pos)));
								open(playerIn, worldIn, pos, state);
							} else {
								playerIn.sendMessage(new UtilTCString(TranslateType.CHAT, "notenoughValue", tag.getInteger("value"), price).applyFormat(TextFormatting.RED));
							}
						} else {
							tag.setIntArray("startPos", UtilBlockPos.getIntArray(anchorPos));
							stack.setTagCompound(tag);
							playerIn.sendMessage(new UtilTCString(TranslateType.CHAT, "setPos", anchorPos.getX(), anchorPos.getY(), anchorPos.getZ()).applyFormat(TextFormatting.WHITE));
							open(playerIn, worldIn, pos, state);
						}
					}
					return true;
				} else {
					playerIn.sendMessage(new UtilTranslatable(TranslateType.CHAT, "missingValue").applyFormat(TextFormatting.RED));
				}
			} else if (stack.getItem() instanceof ItemWrench) {
				if (stack.hasTagCompound()) {
					BlockPos targetPos = UtilBlockPos.getPos(stack.getTagCompound().getIntArray("pos"));
					TileEntity te2 = worldIn.getTileEntity(targetPos);
					System.out.println(targetPos);
					if (te2 instanceof TileEntityAnchor) {
						TileEntityAnchor teA = (TileEntityAnchor) te2;
						if (teTG.isSame(targetPos))
							return true;
						if (!teTG.isBound()) {
							teTG.setAnchor(targetPos);
							teA.addGate(pos);
						} else {
							TileEntity te3 = worldIn.getTileEntity(teTG.getAnchor());
							TileEntityAnchor teA2 = (TileEntityAnchor) te3;
							teA2.deleteGate(pos);
							teTG.setAnchor(targetPos);
							teA.addGate(pos);
						}
						playerIn.sendMessage(new UtilTranslatable(TranslateType.CHAT, "anchorLinked").applyFormat(TextFormatting.GREEN));
					} else {
						playerIn.sendMessage(new UtilTranslatable(TranslateType.CHAT, "illegalAnchor").applyFormat(TextFormatting.RED));
					}
				} else {
					playerIn.sendMessage(new UtilTranslatable(TranslateType.CHAT, "missingAnchor").applyFormat(TextFormatting.RED));
				}
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (!worldIn.isRemote && te instanceof TileEntityTicketGate) {
			if (!((TileEntityTicketGate) te).isBound())
				return;
			TileEntity te2 = worldIn.getTileEntity(((TileEntityTicketGate) te).getAnchor());
			if (te2 instanceof TileEntityAnchor) {
				((TileEntityAnchor) te2).deleteGate(pos);
			}
		}
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getValue(OPEN);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, OPEN });
	}

	public void open(EntityPlayer playerIn, World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos, state.withProperty(OPEN, true));
		worldIn.scheduleBlockUpdate(pos, this, tickRate(worldIn), 5);
		worldIn.playSound(null, pos, SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1, 1);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityTicketGate();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTicketGate();
	}
}
