package chaos.mod.api;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import chaos.mod.util.data.station.Station;
import chaos.mod.util.handlers.StationHandler;
import chaos.mod.util.utils.UtilLogger;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Developers should use this, not using the raw java files like:
 * StationHandler.class.
 *
 * This Api is deprecated since <b>1.4.0</b> and will be replaced with a new Api with more secure operation.
 * 
 * @author ChAoS_UnItY
 */
@Deprecated
public class EkiApi {
	/**
	 * Get list of stations.
	 * 
	 * @return a list of station.
	 */
	public static List<Station> getOverWorldStations() {
		return StationHandler.INSTANCE.getStations();
	}

	/**
	 * Get list of stations' name.
	 * 
	 * @return a list of stations' name.
	 */
	public static List<String> getStationsName() {
		return StationHandler.INSTANCE.getStationsName();
	}

	/**
	 * Get a station by position.
	 * 
	 * @param pos of station to be got.
	 * @return if exists, then return the station matched; if not, then return the
	 *         EXAMPLE station. This prevents return null.
	 */
	public static Station getStationByPos(@Nonnull BlockPos pos) {
		return StationHandler.INSTANCE.getStation(pos);
	}

	/**
	 * Get a station by name.
	 * 
	 * @param name of station to be got.
	 * @return if exists, then return the station matched; if not, then return the
	 *         EXAMPLE station. This prevents return null.
	 */
	public static Station getStationByName(@Nonnull String name, @Nonnull boolean ignoreCase) {
		return StationHandler.INSTANCE.getStation(name, ignoreCase);
	}

	/**
	 * Add stations to the (over)world.
	 * 
	 * @param stations Stations to be added.
	 */
	public static void addStations(@Nonnull Station... sta) {
		Lists.newArrayList(sta).forEach(StationHandler.INSTANCE::addNewStation);
	}

	/**
	 * Replace a station from given position if existed.
	 * 
	 * @param positions of stations to be removed.
	 * @return if successfully replace, return true, otherwise return false.</br>
	 *         <strong> Notice that even if there are some replacing failure, if it
	 *         successfully replaced at least one station, it still returns true.
	 *         </strong>
	 */
	public static boolean replaceStations(@Nonnull Station... sta) {
		int failCount = 0;
		for (int i = 0; i < sta.length; i++)
			if (!StationHandler.INSTANCE.replaceStation(sta[i])) {
				failCount++;
				UtilLogger.info(String.format("Fail to replace a station, given position is %s", sta[i].toString()));
			}
		return failCount != sta.length;
	}

	/**
	 * Remove a station from given position if existed.
	 * 
	 * @param positions of stations to be removed.
	 * @return if successfully removed, return true, otherwise return false.</br>
	 *         <strong> Notice that even if there are some removing failure, if it
	 *         successfully removed at least one station, it still returns true.
	 *         </strong>
	 */
	public static boolean removeStations(@Nonnull BlockPos... pos) {
		int failCount = 0;
		for (int i = 0; i < pos.length; i++)
			if (!StationHandler.INSTANCE.tryRemoveStation(pos[i])) {
				failCount++;
				UtilLogger.info(String.format("Fail to remove a station, given position is %s", pos[i].toString()));
			}
		return failCount != pos.length;
	}

	/**
	 * Check if there is a station at the given position.
	 * 
	 * @return does there a valid station exist at the given position.
	 */
	public static boolean isExists(@Nonnull BlockPos pos) {
		return StationHandler.INSTANCE.isExist(pos);
	}

	/**
	 * Reloads the Station Handler.
	 * 
	 * @param world, this should be overworld.
	 */
	public static void reloadStationHandler(@Nonnull World world) {
		StationHandler.INSTANCE.reload(world);
	}
}
