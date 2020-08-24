package chaos.mod.util.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import chaos.mod.util.data.station.Station;
import chaos.mod.util.utils.UtilLogger;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveHandler;

public class StationHandler {
	public static final StationHandler INSTANCE = new StationHandler();
	private static List<Station> stations;
	private File locationSta;

	public void init(World world) {
		stations = Lists.newArrayList();
		scanAll(getWorldDir(world));
	}

	public void init(List<Station> stations) {
		StationHandler.stations = stations;
	}

	private void scanAll(File path) {
		locationSta = new File(path, "eki-stations");
		locationSta.mkdirs();
		generateExampleStation();

		File[] staFile = locationSta.listFiles();
		UtilLogger.info("=======================================");
		UtilLogger.info("Init Stations......");
		for (int i = 0; i < staFile.length; i++) {
			try {
				register(staFile[i], i);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		UtilLogger.info(String.format("Successfully init %d stations!", staFile.length - 1));
		UtilLogger.info("=======================================");
	}

	private void register(File file, int index) throws FileNotFoundException {
		FileReader reader = new FileReader(file);
		Gson gson = new Gson();
		try {
			JsonReader JSONreader = new JsonReader(reader);
			Station sta = gson.fromJson(JSONreader, Station.class);
			if (sta == null)
				return;
			if (sta.equals(Station.EXAMPLE))
				return;
			stations.add(sta);
			UtilLogger.info(String.format("%s - %s > (%d/%d)", sta.getName(), sta.getPosStringFormat(), index - 1, locationSta.listFiles().length - 1));
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isExist(BlockPos pos) {
		for (Station sta : stations)
			if (sta.getPos().equals(pos))
				return true;
		return false;
	}

	public void addNewStation(Station sta) {
		stations.add(sta);
	}

	public boolean tryRemoveStation(BlockPos pos) {
		for (int i = 0; i < stations.size(); i++)
			if (match(stations.get(i), pos)) {
				try {
					File file = getFile(stations.get(i));
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				stations.remove(i);
				return true;
			}

		return false;
	}

	public boolean replaceStation(Station newSta) {
		for (int i = 0; i < stations.size(); i++)
			if (match(stations.get(i), newSta.getPos())) {
				stations.set(i, newSta);
				return true;
			}
		return false;
	}

	public Station getStation(BlockPos pos) {
		for (Station sta : stations)
			if (match(sta, pos))
				return sta;
		return Station.EXAMPLE;
	}

	public Station getStation(String name, boolean sensitive) {
		for (Station sta : stations)
			if (match(sta, name, sensitive))
				return sta;
		return Station.EXAMPLE;
	}

	public List<Station> getStations() {
		return Lists.newArrayList(stations);
	}

	public List<String> getStationsName() {
		List<String> cache = Lists.newArrayList();
		for (Station s : stations)
			cache.add(s.getName());
		return cache;
	}

	public void saveAll() {
		for (Station sta : stations)
			try {
				save(sta);
			} catch (IOException e) {
				e.printStackTrace();
			}
		stations.clear();
	}

	public void reload(World world) {
		saveAll();
		init(world);
	}

	private void save(Station sta) throws IOException {
		UtilLogger.info("saving");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		FileWriter writer = new FileWriter(getFile(sta));
		gson.toJson(sta, writer);
		writer.close();
	}

	private void generateExampleStation() {
		try {
			save(Station.EXAMPLE);
			UtilLogger.info("Successfully generated example station!");
		} catch (Exception e) {
			UtilLogger.info("Failed to generate example station!");
			e.printStackTrace();
		}
	}

	private File getFile(Station sta) {
		return new File(locationSta, String.format("%s.json", sta.getName() + sta.getPosStringFormat()));
	}

	private boolean match(Station sta, BlockPos pos) {
		return sta.getPos().equals(pos);
	}

	private boolean match(Station sta, String name, boolean sensitive) {
		return sensitive ? sta.getName().equals(name) : sta.getName().equalsIgnoreCase(name);
	}

	private File getWorldDir(World world) {
		ISaveHandler handler = world.getSaveHandler();
		if (!(handler instanceof SaveHandler))
			return null;
		return handler.getWorldDirectory();
	}
}
