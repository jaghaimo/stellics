package stellics.helper;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.JumpPointAPI;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.util.Misc;

import org.lwjgl.util.vector.Vector2f;

public class DistanceHelper {

    public static float getDistanceToPlayerLY(SectorEntityToken entity) {
        CampaignFleetAPI player = Global.getSector().getPlayerFleet();
        if (isInSameLocation(player, entity)) {
            return getDistanceLY(player.getLocation(), entity.getLocation());
        }
        float distance = Misc.getDistanceLY(player.getLocationInHyperspace(), entity.getLocationInHyperspace());
        if (!player.isInHyperspace()) {
            distance += getDistanceToClosestJumpPoint(player);
        }
        if (!entity.isInHyperspace()) {
            distance += getDistanceToClosestJumpPoint(entity);
        }
        return distance;
    }

    private static boolean isInSameLocation(CampaignFleetAPI player, SectorEntityToken entity) {
        LocationAPI playerLocation = player.getContainingLocation();
        LocationAPI entityLocation = entity.getContainingLocation();

        if (playerLocation == null || entityLocation == null) {
            return false;
        }

        return playerLocation == entityLocation;
    }

    private static float getDistanceLY(Vector2f from, Vector2f to) {
        return Misc.getDistance(from, to) / Misc.getUnitsPerLightYear();
    }

    private static float getDistanceToClosestJumpPoint(SectorEntityToken entity) {
        JumpPointAPI jumpPoint = Misc.findNearestJumpPointTo(entity);
        if (jumpPoint == null) {
            return 0;
        }
        return getDistanceLY(jumpPoint.getLocation(), entity.getLocation());
    }
}
