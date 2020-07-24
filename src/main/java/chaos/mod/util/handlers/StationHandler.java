package chaos.mod.util.handlers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;
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
	private File location;

	public void init(World world) {
		stations = Lists.newArrayList();
		scanAll(getWorldDir(world));
	}

	private void scanAll(File path) {
		location = new File(path, "eki-stations");
		location.mkdirs();
		generateExampleStation();

		File[] files = location.listFiles();
		for (int i = 0; i < files.length; i++) {
			register(files[i]);
		}
	}

	private void register(File file) {
		JsonParser parser = new JsonParser();
		try (FileReader reader = new FileReader(file)) {
			Object obj = parser.parse(reader);
			JsonObject json = (JsonObject) obj;

			Station sta = new Station(new BlockPos(json.get("x").getAsInt(), json.get("y").getAsInt(), json.get("z").getAsInt()), json.get("name").getAsString());
			stations.add(sta);
			UtilLogger.info(sta.getName() + " - " + sta.getPos().toString() + " > Successfully Loaded!");
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

	public void addNewStation(Station sta) {
		stations.add(sta);
	}

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

	public Station getStation(BlockPos pos) {
		for (Station sta : stations) {
			if (match(sta, pos)) {
				return sta;
			}
		}
		return Station.EXAMPLE;
	}

	public List<Station> getStations() {
		return Lists.newArrayList(stations.iterator());
	}

	public void saveAll() {
		for (Station sta : stations) {
			try {
				save(sta);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		generateExampleStation();
	}

	private void save(Station sta) throws IOException {
		BlockPos pos = sta.getPos();

		JsonObject object = new JsonObject();
		object.addProperty("name", sta.getName());
		object.addProperty("x", pos.getX());
		object.addProperty("y", pos.getY());
		object.addProperty("z", pos.getZ());

		try (FileWriter writer = new FileWriter(getFile(sta))) {
			writer.write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void generateExampleStation() {
		try {
			save(Station.EXAMPLE);
			stations.add(Station.EXAMPLE);
			UtilLogger.info("Successfully generated example station!");
		} catch (Exception e) {
			UtilLogger.info("Failed to generate example station!");
			e.printStackTrace();
		}
	}

	private File getFile(Station sta) {
		return new File(location, sta.getName() + "(" + sta.getPosStringFormat() + ").json");
	}

	private boolean match(Station sta, BlockPos pos) {
		return sta.getPos().equals(pos);
	}

	private File getWorldDir(World world) {
		ISaveHandler handler = world.getSaveHandler();
		if (!(handler instanceof SaveHandler))
			return null;
		return handler.getWorldDirectory();
	}
}
