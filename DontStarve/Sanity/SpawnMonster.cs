using Microsoft.Xna.Framework;
using StardewModdingAPI;
using StardewValley;
using StardewValley.Monsters;

namespace DontStarve.Sanity;

internal static class SpawnMonster {
    private static long wait;
    private static Random random = new();

    internal static void update(long _) {
        if (wait > 0) {
            wait--;
            return;
        }

        var player = Game1.player;
        var location = Game1.currentLocation;

        if (player.getSanity() <= player.getMaxSanity() * 0.835) {
            var playerPosition = player.Position;
            var xStart = player.Position.X - 15 * Game1.tileSize;
            var xEnd = player.Position.X + 15 * Game1.tileSize;
            var yStart = player.Position.Y - 15 * Game1.tileSize;
            var yEnd = player.Position.Y + 15 * Game1.tileSize;
            Vector2 spawnPosition;
            do {
                spawnPosition = new Vector2(
                    xStart + random.NextSingle() * (xEnd - xStart),
                    yStart + random.NextSingle() * (yEnd - yStart)
                );
            } while (Util.distance(playerPosition, spawnPosition) is > 15 * Game1.tileSize or < 5 * Game1.tileSize);
            location.characters.Add(new Ghost(spawnPosition));
        }
    }

    internal static void sync(long time, long delta) {
        if (delta < 0) {
            wait += -delta;
        } else {
            for (var i = 0; i < delta; i++) {
                update(time);
            }
        }
    }

    internal static void load(IModHelper helper) {
        var data = helper.Data.ReadSaveData<SpawnMonsterData>("DontStarve.Sanity.Monster");
        wait = data?.wait ?? 0;
    }

    internal static void save(IModHelper helper) {
        helper.Data.WriteSaveData("DontStarve.Sanity.Monster", new SpawnMonsterData {
            wait = wait
        });
    }
}

internal class SpawnMonsterData {
    internal long wait { get; init; }
}