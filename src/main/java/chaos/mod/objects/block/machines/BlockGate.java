package chaos.mod.objects.block.machines;

import java.util.List;
import java.util.Random;

import chaos.mod.Eki;
import chaos.mod.init.ItemInit;
import chaos.mod.objects.block.base.BlockHasFace;
import chaos.mod.tileentity.TileEntityAnchor;
import chaos.mod.tileentity.TileEntityTicketGate;
import chaos.mod.util.utils.UtilLogger;
import chaos.mod.util.utils.UtilTextFormer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/***
 * Warning ! This block is missing some logical, to ensure the entire system
 * works fine, you should check every single message from console!
 */
public class BlockGate extends BlockHasFace implements ITileEntityProvider {
	public static final AxisAlignedBB GATE_CLOSED_AABB = new AxisAlignedBB(0, 0, 0, 1, 1.5, 1);
	public static final PropertyBool OPEN = PropertyBool.create("open");

	public BlockGate(String name) {
		super(name, Eki.STATION, false);

		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, false));
	}

	@Override
	public int tickRate(World worldIn) {
		return 50;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			if (state.getValue(OPEN)) {
				worldIn.setBlockState(pos, state.withProperty(OPEN, false));
				worldIn.markBlockRangeForRenderUpdate(pos, pos);
				worldIn.playSound(null, pos, SoundEvent.REGISTRY.getObject(new ResourceLocation("block.iron_door.close")), SoundCategory.BLOCKS, 1, 1);
			}
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		if (state.getValue(OPEN)) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, NULL_AABB);
			return;
		}
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0, 0, 0, 1, 1.5, 1));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		ItemStack stack = playerIn.getHeldItem(hand);
		if (!worldIn.isRemote) {
			if (tileEntity instanceof TileEntityTicketGate) {
				TileEntityTicketGate tileEntityTicketGate = (TileEntityTicketGate) tileEntity;
				try {
					if (stack == null)
						return true;
					if (hasValue(stack, 3)) {
						if (hasValue(stack, 4)) {
							if (tileEntityTicketGate.hasAnchorPos()) {
								clearAnchorIfNeeded(tileEntityTicketGate);
								if (tileEntityTicketGate.isAnchorExists()) {
									TileEntityAnchor tileEntityAnchor = (TileEntityAnchor) worldIn.getTileEntity(tileEntityTicketGate.getAnchorPosForSub());
									if (tileEntityAnchor.getAnchoredObjectsAmount() <= 0) {
										UtilLogger.info("An anchor has been detected unusally. Break this gate might be the solution.");
									}
									tileEntityAnchor.decreaseAnchoredObjectsAmount();
								}
							}
							saveAnchorIn(playerIn, stack, worldIn, tileEntityTicketGate);
							return true;
						}
						missingAnchor(playerIn);
						return true;
					} else if (side == EnumFacing.UP || side == state.getValue(FACING)) {
						if (hasValue(stack, 0)) {
							if (hasValue(stack, 1)) {
								clearAnchorIfNeeded(tileEntityTicketGate);
								savePosIntoItem(hand, playerIn, tileEntityTicketGate, pos);
								open(playerIn, worldIn, pos, state);
								return true;
							}
							if (hasValue(stack, 2)) {
								clearAnchorIfNeeded(tileEntityTicketGate);
								int price = calculatePrice(stack.getTagCompound().getIntArray("startPos"), tileEntityTicketGate.getAnchorPos());
								int length = calculateLength(stack, pos, worldIn);
								if (getPosFromItem(stack, pos, worldIn)) {
									ticketAccessible(playerIn, hand, pos, price, length);
									open(playerIn, worldIn, pos, state);
									return true;
								}
								ticketInaccessible(playerIn, stack, price);
								return true;
							}
						} else if (stack.getItem() == ItemInit.TICKET && !stack.hasTagCompound()) {
							missingValue(playerIn);
						}
					}
				} catch (NullPointerException e) {
					UtilLogger.info("A NullPointerExcception has just been occured. Please report this to [Eki Mod] author.");
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (!worldIn.isRemote) {
			if (tileEntity instanceof TileEntityTicketGate) {
				TileEntityTicketGate tileEntityTicketGate = (TileEntityTicketGate) tileEntity;
				BlockPos pos2 = tileEntityTicketGate.getAnchorPosForSub();
				TileEntity tileEntity2 = worldIn.getTileEntity(pos2);
				if (tileEntity2 instanceof TileEntityAnchor) {
					TileEntityAnchor tileEntityAnchor = (TileEntityAnchor) tileEntity2;
					if (tileEntityAnchor.getAnchoredObjectsAmount() <= 0)
						return;
					tileEntityAnchor.decreaseAnchoredObjectsAmount();
				}
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

	/***
	 * 
	 * @param stack
	 * @param type  put 0 to check if this is a ticket with any nbt tag. Put 1 to
	 *              check if this ticket has value but doesn't have startPos. Put 2
	 *              to check if this ticket has both value and startPos. Put 3 to
	 *              check if this is a wrench. Put 4 to check if (this wrench or)
	 *              item has nbt tag.
	 * @return final result of checker.
	 */
	private boolean hasValue(ItemStack stack, int type) {
		switch (type) {
		case 0:
			return stack.getItem() == ItemInit.TICKET && stack.hasTagCompound();
		case 1:
			return stack.getTagCompound().hasKey("value") && !stack.getTagCompound().hasKey("startPos");
		case 2:
			return stack.getTagCompound().hasKey("value") && stack.getTagCompound().hasKey("startPos");
		case 3:
			return stack.getItem() == ItemInit.WRENCH;
		case 4:
			return stack.hasTagCompound();
		default:
			return true;
		}

	}

	private void savePosIntoItem(EnumHand hand, EntityPlayer playerIn, TileEntityTicketGate tileEntityTicketGate, BlockPos pos) {
		NBTTagCompound nbt = new NBTTagCompound();
		ItemStack stack2 = new ItemStack(ItemInit.TICKET, 1);
		TextComponentString textSetPos = new TextComponentString(I18n.format("chat.type.text.setpos", pos.getX(), pos.getY(), pos.getZ()));
		nbt.setInteger("value", playerIn.getHeldItem(hand).getTagCompound().getInteger("value"));
		if (tileEntityTicketGate.isAnchorExists()) {
			savePosIntoItem(nbt, tileEntityTicketGate);
		} else {
			savePosIntoItem(nbt, pos);
		}
		stack2.setTagCompound(nbt);
		playerIn.getHeldItem(hand).stackSize--;
		playerIn.entityDropItem(stack2, (float) playerIn.getYOffset());
		playerIn.addChatMessage(UtilTextFormer.form(textSetPos, TextFormatting.GREEN, false, false));
	}

	private void ticketAccessible(EntityPlayer playerIn, EnumHand hand, BlockPos pos, int price, int length) {
		TextComponentString textAccessed = new TextComponentString(I18n.format("chat.type.text.accessed", pos.getX(), pos.getY(), pos.getZ(), price, length));
		playerIn.addChatMessage(UtilTextFormer.form(textAccessed, TextFormatting.GREEN, false, false));
		playerIn.getHeldItem(hand).stackSize--;
	}

	private void ticketInaccessible(EntityPlayer playerIn, ItemStack stack, int Actaulprice) {
		int price = stack.getTagCompound().getInteger("value");
		TextComponentString textNotEnoughValue = new TextComponentString(I18n.format("chat.type.text.notenoughvalue", price, Actaulprice));
		playerIn.addChatMessage(UtilTextFormer.form(textNotEnoughValue, TextFormatting.RED, true, true));
	}

	private void missingValue(EntityPlayer playerIn) {
		TextComponentString textMissingValue = new TextComponentString(I18n.format("chat.type.text.missingvalue"));
		playerIn.addChatMessage(UtilTextFormer.form(textMissingValue, TextFormatting.RED, true, true));
	}

	private void missingAnchor(EntityPlayer playerIn) {
		TextComponentString textMissingAnchor = new TextComponentString(I18n.format("chat.type.text.missinganchor"));
		playerIn.addChatMessage(UtilTextFormer.form(textMissingAnchor, TextFormatting.RED, true, true));
	}

	/***
	 * Get a new Itemstack stored this gate's anchor position.
	 * 
	 * @param stack
	 * @return a new nbt tag with position.
	 */
	public NBTTagCompound savePosIntoItem(NBTTagCompound nbt, TileEntityTicketGate tileEntity) {
		int[] startPos = { tileEntity.getAnchorPos()[0], tileEntity.getAnchorPos()[1] };
		nbt.setIntArray("startPos", startPos);
		return nbt;
	}

	/***
	 * Get a new Itemstack stored this position.
	 * 
	 * @param stack
	 * @return a new nbt tag with position.
	 */
	public NBTTagCompound savePosIntoItem(NBTTagCompound nbt, BlockPos pos) {
		int[] startPos = { pos.getX(), pos.getZ() };
		nbt.setIntArray("startPos", startPos);
		return nbt;
	}

	/***
	 * Get the boolean if this ticket is valuable to access this ticket gate.
	 * 
	 * @param stack
	 * @return is ticket is valuable to access this ticket gate
	 */
	public boolean getPosFromItem(ItemStack stack, BlockPos pos, World worldIn) {
		TileEntityTicketGate tileEntity = (TileEntityTicketGate) worldIn.getTileEntity(pos);
		int[] startPos = stack.getTagCompound().getIntArray("startPos");
		int price = tileEntity.isAnchorExists() ? calculatePrice(startPos, tileEntity.getAnchorPos()) : calculatePrice(startPos, new int[] { pos.getX(), pos.getZ() });
		return price <= stack.getTagCompound().getInteger("value");
	}

	public void saveAnchorIn(EntityPlayer playerIn, ItemStack stack, World worldIn, TileEntityTicketGate tileEntityTicketGate) {
		TextComponentString textLinked = new TextComponentString(I18n.format("chat.type.text.anchorlinked"));
		int[] array = stack.getTagCompound().getIntArray("pos");
		if (!(worldIn.getTileEntity(new BlockPos(array[0], array[2], array[1])) == null)) {
			TileEntityAnchor tileEntityAnchor = (TileEntityAnchor) worldIn.getTileEntity(new BlockPos(array[0], array[2], array[1]));
			tileEntityAnchor.increaseAnchoredObjectsAmount();
		} else {
			UtilLogger.info("Your anchor is missing, it won't affect the proccess but some calculation won't be correct.");
		}
		tileEntityTicketGate.saveAnchorPosIn(array[0], array[1], array[2]);
		playerIn.addChatMessage(textLinked);
	}

	public void clearAnchorIfNeeded(TileEntityTicketGate tileEntityTicketGate) {
		if (!tileEntityTicketGate.isAnchorExists()) {
			tileEntityTicketGate.clearAnchorPos();
		}
	}

	public int calculatePrice(int[] startPos, int[] endPos) {
		int price = (int) (Math.round(Math.sqrt(Math.pow(endPos[0] - startPos[0], 2) + Math.pow(endPos[1] - startPos[1], 2))) * 100);
		return price;
	}

	public int calculateLength(ItemStack stack, BlockPos pos, World worldIn) {
		TileEntityTicketGate tileEntity = (TileEntityTicketGate) worldIn.getTileEntity(pos);
		int[] startPos = stack.getTagCompound().getIntArray("startPos");
		int length = calculatePrice(startPos, tileEntity.getAnchorPos()) / 100;
		return length;
	}

	public void open(EntityPlayer playerIn, World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos, state.withProperty(OPEN, true));
		worldIn.scheduleBlockUpdate(pos, this, this.tickRate(worldIn), 5);
		worldIn.playSound(null, pos, SoundEvent.REGISTRY.getObject(new ResourceLocation("block.iron_door.open")), SoundCategory.BLOCKS, 1, 1);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTicketGate();
	}
}
