package chaos.mod.util.data.station;

import java.util.UUID;

import com.google.gson.JsonArray;

public class Player extends DataForm {
	private String name;
	private UUID uuid;

	public Player(String name, UUID uuid) {
		this.name = name;
		this.uuid = uuid;
	}

	public Player(String name, String uuid) {
		this(name, UUID.fromString(uuid));
	}

	public String getName() {
		return name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String[] toArray() {
		return new String[] { name, uuid.toString() };
	}

	/***
	 * to Json Format. Use pretty GSON to make it pretty of course.
	 */
	public JsonArray getJsonArray() {
		JsonArray array = new JsonArray();
		array.add(name);
		array.add(uuid.toString());
		return array;
	}

	@Override
	public String getData() {
		return name;
	}

	@Override
	public String[] toStringArray() {
		return new String[] { name, uuid.toString() };
	}

	@Override
	public boolean equals(Object obj) {
		return ((Player) obj).getUuid() == uuid;
	}
}
