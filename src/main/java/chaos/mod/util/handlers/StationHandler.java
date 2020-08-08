package chaos.mod.util.handlers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import chaos.mod.util.data.station.Station;
import chaos.mod.util.utils.UtilLogger;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveHandler;

public class StationHandler {
	public static final StationHandler INSTANCE = new StationHandler();
	private static List<Station> stations;
	//private static List<Company> companies;
	private File locationSta;
	//private File locationCom;

	public void init(World world) {
		stations = Lists.newArrayList();
		//companies = Lists.newArrayList();
		scanAll(getWorldDir(world));
	}

	public void init(List<Station> stations) {
		StationHandler.stations = stations;
		//StationHandler.companies = companies;
	}

	private void scanAll(File path) {
		locationSta = new File(path, "eki-stations");
		locationSta.mkdirs();
		generateExampleStation();

		File[] staFile = locationSta.listFiles();
		UtilLogger.info("=======================================");
		UtilLogger.info("Init Stations......");
		for (int i = 0; i < staFile.length; i++) {
			register(staFile[i], i, RegisterType.STA);
		}
		UtilLogger.info("Successfully init " + (staFile.length - 1) + " stations!");
		UtilLogger.info("=======================================");

		/*locationCom = new File(path, "eki-companies");
		locationCom.mkdirs();
		generateExampleCompany();

		File[] comFile = locationCom.listFiles();
		UtilLogger.info("=======================================");
		UtilLogger.info("Init Companies......");
		for (int i = 0; i < comFile.length; i++) {
			register(comFile[i], i, RegisterType.COM);
		}
		UtilLogger.info("Successfully init " + (comFile.length - 1) + " companies!");
		UtilLogger.info("=======================================");*/
	}

	private void register(File file, int index, RegisterType type) {
		JsonParser parser = new JsonParser();
		try (FileReader reader = new FileReader(file)) {
			Object obj = parser.parse(reader);
			JsonObject json = (JsonObject) obj;

			switch (type) {
			case STA:
				Station sta = new Station(new BlockPos(json.get("x").getAsInt(), json.get("y").getAsInt(), json.get("z").getAsInt()), json.get("name").getAsString());
				if (sta.equals(Station.EXAMPLE))
					return;
				stations.add(sta);
				UtilLogger.info(sta.getName() + " - " + sta.getPosStringFormat() + " > (" + index + "/" + (locationSta.listFiles().length - 1) + ")");
				break;
			/*case COM:
				JsonArray arrOwner = json.get("owner").getAsJsonArray();
				JsonArray arrStaff = json.get("staff").getAsJsonArray();
				List<Player> staff = Lists.newArrayList();
				for (int i = 0; i < arrStaff.size(); i++) {
					JsonArray arrStaffDetail = arrStaff.get(i).getAsJsonArray();
					staff.add(new Player(arrStaffDetail.get(0).getAsString(), arrStaffDetail.get(1).getAsString()));
				}
				Company com = new Company(json.get("name").getAsString(), new Player(arrOwner.get(0).getAsString(), arrOwner.get(1).getAsString()), staff);
				if (com.equals(Company.EXAMPLE))
					return;
				companies.add(com);
				UtilLogger.info(com.getName() + " - Owned by : " + com.getOwner().getName() + " > (" + index + "/" + (locationCom.listFiles().length - 1) + ")");
				break;*/
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isExist(BlockPos pos) {
		for (Station sta : stations) {
			if (sta.getPos().equals(pos)) {
				return true;
			}
		}
		return false;
	}

	/*public boolean isExist(UUID uuid) {
		for (Company com : companies) {
			if (com.getOwner().getUuid().equals(uuid)) {
				return true;
			}
		}
		return false;
	}*/

	public void addNewStation(Station sta) {
		stations.add(sta);
	}

	/*public void addNewCompany(Company com) {
		companies.add(com);
	}*/

	public boolean tryRemoveStation(BlockPos pos) {
		for (int i = 0; i < stations.size(); i++) {
			if (match(stations.get(i), pos)) {
				stations.remove(i);
				try {
					File file = getFile(stations.get(i));
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}

	/*public boolean tryRemoveCompany(UUID uuid, String name, boolean isAdmin) {
		if (isAdmin && name != null) {
			for (int i = 0; i < companies.size(); i++) {
				if (match(companies.get(i), name)) {
					companies.remove(i);
					try {
						File file = getFile(companies.get(i));
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return true;
				}
			}
		}
		if (!(uuid == null)) {
			for (int i = 0; i < companies.size(); i++) {
				if (match(companies.get(i), uuid)) {
					companies.remove(i);
					try {
						File file = getFile(companies.get(i));
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return true;
				}
			}
		}
		return false;
	}*/

	public Station getStation(BlockPos pos) {
		for (Station sta : stations) {
			if (match(sta, pos)) {
				return sta;
			}
		}
		return Station.EXAMPLE;
	}

	/*public Company getCompany(UUID uuid, String name) {
		if (uuid == null) {
			for (Company com : companies) {
				if (match(com, name)) {
					return com;
				}
			}
		} else if (name == null) {
			for (Company com : companies) {
				if (match(com, uuid)) {
					return com;
				}
			}
		}
		return Company.EXAMPLE;
	}*/

	public List<Station> getStations() {
		return Lists.newArrayList(stations.iterator());
	}

	/*public List<Company> getCompanies() {
		return Lists.newArrayList(companies.iterator());
	}*/

	public void saveAll() {
		for (Station sta : stations) {
			try {
				save(sta);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		stations.clear();
		/*for (Company com : companies) {
			try {
				save(com);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		companies.clear();*/
	}

	public void reload(World world) {
		saveAll();
		init(world);
	}

	private void save(Station sta) throws IOException {
		BlockPos pos = sta.getPos();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JsonObject object = new JsonObject();
		object.addProperty("name", sta.getName());
		object.addProperty("x", pos.getX());
		object.addProperty("y", pos.getY());
		object.addProperty("z", pos.getZ());

		JsonParser parser = new JsonParser();

		try (FileWriter writer = new FileWriter(getFile(sta))) {
			writer.write(gson.toJson(parser.parse(object.toString())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*private void save(Company com) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JsonObject object = new JsonObject();
		object.addProperty("name", com.getName());
		object.add("owner", com.getOwner().getJsonArray());
		object.add("staff", com.staffGetJsonArray());

		JsonParser parser = new JsonParser();

		try (FileWriter writer = new FileWriter(getFile(com))) {
			writer.write(gson.toJson(parser.parse(object.toString())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	private void generateExampleStation() {
		try {
			save(Station.EXAMPLE);
			UtilLogger.info("Successfully generated example station!");
		} catch (Exception e) {
			UtilLogger.info("Failed to generate example station!");
			e.printStackTrace();
		}
	}

	/*private void generateExampleCompany() {
		try {
			save(Company.EXAMPLE);
			UtilLogger.info("Successfully generated example company!");
		} catch (Exception e) {
			UtilLogger.info("Failed to generate example company!");
			e.printStackTrace();
		}
	}*/

	private File getFile(Station sta) {
		return new File(locationSta, sta.getName() + sta.getPosStringFormat() + ".json");
	}

	/*private File getFile(Company com) {
		return new File(locationCom, com.getName() + "-" + com.getOwner().getName() + ".json");
	}*/

	private boolean match(Station sta, BlockPos pos) {
		return sta.getPos().equals(pos);
	}

	/*private boolean match(Company com, UUID uuid) {
		return com.getOwner().getUuid().equals(uuid);
	}

	private boolean match(Company com, String name) {
		return com.getName().equals(name);
	}*/

	private File getWorldDir(World world) {
		ISaveHandler handler = world.getSaveHandler();
		if (!(handler instanceof SaveHandler))
			return null;
		return handler.getWorldDirectory();
	}

	public enum RegisterType {
		STA, COM;
	}
}
