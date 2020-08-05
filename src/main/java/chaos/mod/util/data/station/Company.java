package chaos.mod.util.data.station;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;

public class Company extends DataForm {
	public static final Company EXAMPLE = new Company("EXAMPLE_COMPANY", new Player("ChAoS_UnItY", "7610657d-712b-479e-a6f2-56d69ae13d04"),
			Lists.newArrayList(new Player("eeedx", "c806269a-fce5-43af-9ffb-fde9e69314c8")));

	private String name;
	private Player owner;
	private List<Player> staff;

	@SuppressWarnings("unused")
	private Company(String name, Player owner, Player... staff) {
		this.name = name;
		this.owner = owner;
		this.staff = Lists.newArrayList();
		for (int i = 0; i < staff.length; i++) {
			this.staff.add(staff[i]);
		}
	}

	private Company(String name, Player owner, List<Player> staff) {
		this.name = name;
		this.owner = owner;
		this.staff = Lists.newArrayList();
		for (int i = 0; i < staff.size(); i++) {
			this.staff.add(staff.get(i));
		}
	}

	public Company(String name, Player owner) {
		this.name = name;
		this.owner = owner;
		staff = Lists.newArrayList();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public void addStaff(Player player) {
		staff.add(player);
	}

	/***
	 * @param player to remove
	 * @return true: real player removed, false: no player matched.
	 */
	public boolean removeStaff(Player player) {
		for (int i = 0; i < staff.size(); i++) {
			if (player.equals(staff.get(i))) {
				staff.remove(i);
				return true;
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public Player getOwner() {
		return owner;
	}

	public List<Player> getStaff() {
		return staff;
	}

	public boolean hasStaff() {
		return staff.isEmpty();
	}

	/***
	 * @return List with owner and staff
	 */
	public List<Player> getAllMemebers() {
		List<Player> member = Lists.newArrayList(staff.iterator());
		member.add(owner);
		return member;
	}

	public JsonArray staffGetJsonArray() {
		JsonArray array = new JsonArray();
		for (Player player : staff) {
			array.add(player.getJsonArray());
		}
		return array;
	}

	@Override
	public String getData() {
		return null;
	}

}
