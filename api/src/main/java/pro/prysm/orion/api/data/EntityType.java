package pro.prysm.orion.api.data;

import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import pro.prysm.orion.api.entity.Entity;
import pro.prysm.orion.api.util.CollectorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class EntityType {
    private static final List<EntityType> REGISTRY = new ArrayList<>(Arrays.asList(
        // Begin auto generated code
        new EntityType(0, 0, "area_effect_cloud", "Area Effect Cloud", 6, 0.5, Entity.Category.OTHER),
		new EntityType(1, 1, "armor_stand", "Armor Stand", 0.5, 1.975, Entity.Category.LIVING),
		new EntityType(2, 2, "arrow", "Arrow", 0.5, 0.5, Entity.Category.PROJECTILE),
		new EntityType(3, 3, "axolotl", "Axolotl", 0.75, 0.42, Entity.Category.ANIMAL),
		new EntityType(4, 4, "bat", "Bat", 0.5, 0.9, Entity.Category.AMBIENT),
		new EntityType(5, 5, "bee", "Bee", 0.7, 0.6, Entity.Category.ANIMAL),
		new EntityType(6, 6, "blaze", "Blaze", 0.6, 1.8, Entity.Category.HOSTILE),
		new EntityType(7, 7, "boat", "Boat", 1.375, 0.5625, Entity.Category.OTHER),
		new EntityType(8, 8, "cat", "Cat", 0.6, 0.7, Entity.Category.ANIMAL),
		new EntityType(9, 9, "cave_spider", "Cave Spider", 0.7, 0.5, Entity.Category.HOSTILE),
		new EntityType(10, 10, "chicken", "Chicken", 0.4, 0.7, Entity.Category.ANIMAL),
		new EntityType(11, 11, "cod", "Cod", 0.5, 0.3, Entity.Category.WATER_CREATURE),
		new EntityType(12, 12, "cow", "Cow", 0.9, 1.4, Entity.Category.ANIMAL),
		new EntityType(13, 13, "creeper", "Creeper", 0.6, 1.7, Entity.Category.HOSTILE),
		new EntityType(14, 14, "dolphin", "Dolphin", 0.9, 0.6, Entity.Category.WATER_CREATURE),
		new EntityType(15, 15, "donkey", "Donkey", 1.39648, 1.5, Entity.Category.ANIMAL),
		new EntityType(16, 16, "dragon_fireball", "Dragon Fireball", 1, 1, Entity.Category.PROJECTILE),
		new EntityType(17, 17, "drowned", "Drowned", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(18, 18, "elder_guardian", "Elder Guardian", 1.9975, 1.9975, Entity.Category.HOSTILE),
		new EntityType(19, 19, "end_crystal", "End Crystal", 2, 2, Entity.Category.OTHER),
		new EntityType(20, 20, "ender_dragon", "Ender Dragon", 16, 8, Entity.Category.MOB),
		new EntityType(21, 21, "enderman", "Enderman", 0.6, 2.9, Entity.Category.HOSTILE),
		new EntityType(22, 22, "endermite", "Endermite", 0.4, 0.3, Entity.Category.HOSTILE),
		new EntityType(23, 23, "evoker", "Evoker", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(24, 24, "evoker_fangs", "Evoker Fangs", 0.5, 0.8, Entity.Category.OTHER),
		new EntityType(25, 25, "experience_orb", "Experience Orb", 0.5, 0.5, Entity.Category.OTHER),
		new EntityType(26, 26, "eye_of_ender", "Eye of Ender", 0.25, 0.25, Entity.Category.OTHER),
		new EntityType(27, 27, "falling_block", "Falling Block", 0.98, 0.98, Entity.Category.OTHER),
		new EntityType(28, 28, "firework_rocket", "Firework Rocket", 0.25, 0.25, Entity.Category.PROJECTILE),
		new EntityType(29, 29, "fox", "Fox", 0.6, 0.7, Entity.Category.ANIMAL),
		new EntityType(30, 30, "ghast", "Ghast", 4, 4, Entity.Category.MOB),
		new EntityType(31, 31, "giant", "Giant", 3.6, 12, Entity.Category.HOSTILE),
		new EntityType(32, 32, "glow_item_frame", "Glow Item Frame", 0.5, 0.5, Entity.Category.OTHER),
		new EntityType(33, 33, "glow_squid", "Glow Squid", 0.8, 0.8, Entity.Category.WATER_CREATURE),
		new EntityType(34, 34, "goat", "Goat", 0.9, 1.3, Entity.Category.ANIMAL),
		new EntityType(35, 35, "guardian", "Guardian", 0.85, 0.85, Entity.Category.HOSTILE),
		new EntityType(36, 36, "hoglin", "Hoglin", 1.39648, 1.4, Entity.Category.ANIMAL),
		new EntityType(37, 37, "horse", "Horse", 1.39648, 1.6, Entity.Category.ANIMAL),
		new EntityType(38, 38, "husk", "Husk", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(39, 39, "illusioner", "Illusioner", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(40, 40, "iron_golem", "Iron Golem", 1.4, 2.7, Entity.Category.MOB),
		new EntityType(41, 41, "item", "Item", 0.25, 0.25, Entity.Category.OTHER),
		new EntityType(42, 42, "item_frame", "Item Frame", 0.5, 0.5, Entity.Category.OTHER),
		new EntityType(43, 43, "fireball", "Fireball", 1, 1, Entity.Category.PROJECTILE),
		new EntityType(44, 44, "leash_knot", "Leash Knot", 0.375, 0.5, Entity.Category.OTHER),
		new EntityType(45, 45, "lightning_bolt", "Lightning Bolt", 0, 0, Entity.Category.OTHER),
		new EntityType(46, 46, "llama", "Llama", 0.9, 1.87, Entity.Category.ANIMAL),
		new EntityType(47, 47, "llama_spit", "Llama Spit", 0.25, 0.25, Entity.Category.PROJECTILE),
		new EntityType(48, 48, "magma_cube", "Magma Cube", 2.04, 2.04, Entity.Category.MOB),
		new EntityType(49, 49, "marker", "Marker", 0, 0, Entity.Category.OTHER),
		new EntityType(50, 50, "minecart", "Minecart", 0.98, 0.7, Entity.Category.OTHER),
		new EntityType(51, 51, "chest_minecart", "Minecart with Chest", 0.98, 0.7, Entity.Category.OTHER),
		new EntityType(52, 52, "command_block_minecart", "Minecart with Command Block", 0.98, 0.7, Entity.Category.OTHER),
		new EntityType(53, 53, "furnace_minecart", "Minecart with Furnace", 0.98, 0.7, Entity.Category.OTHER),
		new EntityType(54, 54, "hopper_minecart", "Minecart with Hopper", 0.98, 0.7, Entity.Category.OTHER),
		new EntityType(55, 55, "spawner_minecart", "Minecart with Spawner", 0.98, 0.7, Entity.Category.OTHER),
		new EntityType(56, 56, "tnt_minecart", "Minecart with TNT", 0.98, 0.7, Entity.Category.OTHER),
		new EntityType(57, 57, "mule", "Mule", 1.39648, 1.6, Entity.Category.ANIMAL),
		new EntityType(58, 58, "mooshroom", "Mooshroom", 0.9, 1.4, Entity.Category.ANIMAL),
		new EntityType(59, 59, "ocelot", "Ocelot", 0.6, 0.7, Entity.Category.ANIMAL),
		new EntityType(60, 60, "painting", "Painting", 0.5, 0.5, Entity.Category.OTHER),
		new EntityType(61, 61, "panda", "Panda", 1.3, 1.25, Entity.Category.ANIMAL),
		new EntityType(62, 62, "parrot", "Parrot", 0.5, 0.9, Entity.Category.ANIMAL),
		new EntityType(63, 63, "phantom", "Phantom", 0.9, 0.5, Entity.Category.MOB),
		new EntityType(64, 64, "pig", "Pig", 0.9, 0.9, Entity.Category.ANIMAL),
		new EntityType(65, 65, "piglin", "Piglin", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(66, 66, "piglin_brute", "Piglin Brute", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(67, 67, "pillager", "Pillager", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(68, 68, "polar_bear", "Polar Bear", 1.4, 1.4, Entity.Category.ANIMAL),
		new EntityType(69, 69, "tnt", "Primed TNT", 0.98, 0.98, Entity.Category.OTHER),
		new EntityType(70, 70, "pufferfish", "Pufferfish", 0.7, 0.7, Entity.Category.WATER_CREATURE),
		new EntityType(71, 71, "rabbit", "Rabbit", 0.4, 0.5, Entity.Category.ANIMAL),
		new EntityType(72, 72, "ravager", "Ravager", 1.95, 2.2, Entity.Category.HOSTILE),
		new EntityType(73, 73, "salmon", "Salmon", 0.7, 0.4, Entity.Category.WATER_CREATURE),
		new EntityType(74, 74, "sheep", "Sheep", 0.9, 1.3, Entity.Category.ANIMAL),
		new EntityType(75, 75, "shulker", "Shulker", 1, 1, Entity.Category.MOB),
		new EntityType(76, 76, "shulker_bullet", "Shulker Bullet", 0.3125, 0.3125, Entity.Category.PROJECTILE),
		new EntityType(77, 77, "silverfish", "Silverfish", 0.4, 0.3, Entity.Category.HOSTILE),
		new EntityType(78, 78, "skeleton", "Skeleton", 0.6, 1.99, Entity.Category.HOSTILE),
		new EntityType(79, 79, "skeleton_horse", "Skeleton Horse", 1.39648, 1.6, Entity.Category.ANIMAL),
		new EntityType(80, 80, "slime", "Slime", 2.04, 2.04, Entity.Category.MOB),
		new EntityType(81, 81, "small_fireball", "Small Fireball", 0.3125, 0.3125, Entity.Category.PROJECTILE),
		new EntityType(82, 82, "snow_golem", "Snow Golem", 0.7, 1.9, Entity.Category.MOB),
		new EntityType(83, 83, "snowball", "Snowball", 0.25, 0.25, Entity.Category.PROJECTILE),
		new EntityType(84, 84, "spectral_arrow", "Spectral Arrow", 0.5, 0.5, Entity.Category.PROJECTILE),
		new EntityType(85, 85, "spider", "Spider", 1.4, 0.9, Entity.Category.HOSTILE),
		new EntityType(86, 86, "squid", "Squid", 0.8, 0.8, Entity.Category.WATER_CREATURE),
		new EntityType(87, 87, "stray", "Stray", 0.6, 1.99, Entity.Category.HOSTILE),
		new EntityType(88, 88, "strider", "Strider", 0.9, 1.7, Entity.Category.ANIMAL),
		new EntityType(89, 89, "egg", "Thrown Egg", 0.25, 0.25, Entity.Category.PROJECTILE),
		new EntityType(90, 90, "ender_pearl", "Thrown Ender Pearl", 0.25, 0.25, Entity.Category.PROJECTILE),
		new EntityType(91, 91, "experience_bottle", "Thrown Bottle o' Enchanting", 0.25, 0.25, Entity.Category.PROJECTILE),
		new EntityType(92, 92, "potion", "Potion", 0.25, 0.25, Entity.Category.PROJECTILE),
		new EntityType(93, 93, "trident", "Trident", 0.5, 0.5, Entity.Category.PROJECTILE),
		new EntityType(94, 94, "trader_llama", "Trader Llama", 0.9, 1.87, Entity.Category.ANIMAL),
		new EntityType(95, 95, "tropical_fish", "Tropical Fish", 0.5, 0.4, Entity.Category.WATER_CREATURE),
		new EntityType(96, 96, "turtle", "Turtle", 1.2, 0.4, Entity.Category.ANIMAL),
		new EntityType(97, 97, "vex", "Vex", 0.4, 0.8, Entity.Category.HOSTILE),
		new EntityType(98, 98, "villager", "Villager", 0.6, 1.95, Entity.Category.PASSIVE),
		new EntityType(99, 99, "vindicator", "Vindicator", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(100, 100, "wandering_trader", "Wandering Trader", 0.6, 1.95, Entity.Category.PASSIVE),
		new EntityType(101, 101, "witch", "Witch", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(102, 102, "wither", "Wither", 0.9, 3.5, Entity.Category.HOSTILE),
		new EntityType(103, 103, "wither_skeleton", "Wither Skeleton", 0.7, 2.4, Entity.Category.HOSTILE),
		new EntityType(104, 104, "wither_skull", "Wither Skull", 0.3125, 0.3125, Entity.Category.PROJECTILE),
		new EntityType(105, 105, "wolf", "Wolf", 0.6, 0.85, Entity.Category.ANIMAL),
		new EntityType(106, 106, "zoglin", "Zoglin", 1.39648, 1.4, Entity.Category.HOSTILE),
		new EntityType(107, 107, "zombie", "Zombie", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(108, 108, "zombie_horse", "Zombie Horse", 1.39648, 1.6, Entity.Category.ANIMAL),
		new EntityType(109, 109, "zombie_villager", "Zombie Villager", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(110, 110, "zombified_piglin", "Zombified Piglin", 0.6, 1.95, Entity.Category.HOSTILE),
		new EntityType(111, 111, "player", "Player", 0.6, 1.8, Entity.Category.PLAYER),
		new EntityType(112, 112, "fishing_bobber", "Fishing Bobber", 0.25, 0.25, Entity.Category.PROJECTILE)
        // End auto generated code
    ));

    // Begin auto generated code
    public static final EntityType AREA_EFFECT_CLOUD = getById(0);
	public static final EntityType ARMOR_STAND = getById(1);
	public static final EntityType ARROW = getById(2);
	public static final EntityType AXOLOTL = getById(3);
	public static final EntityType BAT = getById(4);
	public static final EntityType BEE = getById(5);
	public static final EntityType BLAZE = getById(6);
	public static final EntityType BOAT = getById(7);
	public static final EntityType CAT = getById(8);
	public static final EntityType CAVE_SPIDER = getById(9);
	public static final EntityType CHICKEN = getById(10);
	public static final EntityType COD = getById(11);
	public static final EntityType COW = getById(12);
	public static final EntityType CREEPER = getById(13);
	public static final EntityType DOLPHIN = getById(14);
	public static final EntityType DONKEY = getById(15);
	public static final EntityType DRAGON_FIREBALL = getById(16);
	public static final EntityType DROWNED = getById(17);
	public static final EntityType ELDER_GUARDIAN = getById(18);
	public static final EntityType END_CRYSTAL = getById(19);
	public static final EntityType ENDER_DRAGON = getById(20);
	public static final EntityType ENDERMAN = getById(21);
	public static final EntityType ENDERMITE = getById(22);
	public static final EntityType EVOKER = getById(23);
	public static final EntityType EVOKER_FANGS = getById(24);
	public static final EntityType EXPERIENCE_ORB = getById(25);
	public static final EntityType EYE_OF_ENDER = getById(26);
	public static final EntityType FALLING_BLOCK = getById(27);
	public static final EntityType FIREWORK_ROCKET = getById(28);
	public static final EntityType FOX = getById(29);
	public static final EntityType GHAST = getById(30);
	public static final EntityType GIANT = getById(31);
	public static final EntityType GLOW_ITEM_FRAME = getById(32);
	public static final EntityType GLOW_SQUID = getById(33);
	public static final EntityType GOAT = getById(34);
	public static final EntityType GUARDIAN = getById(35);
	public static final EntityType HOGLIN = getById(36);
	public static final EntityType HORSE = getById(37);
	public static final EntityType HUSK = getById(38);
	public static final EntityType ILLUSIONER = getById(39);
	public static final EntityType IRON_GOLEM = getById(40);
	public static final EntityType ITEM = getById(41);
	public static final EntityType ITEM_FRAME = getById(42);
	public static final EntityType FIREBALL = getById(43);
	public static final EntityType LEASH_KNOT = getById(44);
	public static final EntityType LIGHTNING_BOLT = getById(45);
	public static final EntityType LLAMA = getById(46);
	public static final EntityType LLAMA_SPIT = getById(47);
	public static final EntityType MAGMA_CUBE = getById(48);
	public static final EntityType MARKER = getById(49);
	public static final EntityType MINECART = getById(50);
	public static final EntityType CHEST_MINECART = getById(51);
	public static final EntityType COMMAND_BLOCK_MINECART = getById(52);
	public static final EntityType FURNACE_MINECART = getById(53);
	public static final EntityType HOPPER_MINECART = getById(54);
	public static final EntityType SPAWNER_MINECART = getById(55);
	public static final EntityType TNT_MINECART = getById(56);
	public static final EntityType MULE = getById(57);
	public static final EntityType MOOSHROOM = getById(58);
	public static final EntityType OCELOT = getById(59);
	public static final EntityType PAINTING = getById(60);
	public static final EntityType PANDA = getById(61);
	public static final EntityType PARROT = getById(62);
	public static final EntityType PHANTOM = getById(63);
	public static final EntityType PIG = getById(64);
	public static final EntityType PIGLIN = getById(65);
	public static final EntityType PIGLIN_BRUTE = getById(66);
	public static final EntityType PILLAGER = getById(67);
	public static final EntityType POLAR_BEAR = getById(68);
	public static final EntityType TNT = getById(69);
	public static final EntityType PUFFERFISH = getById(70);
	public static final EntityType RABBIT = getById(71);
	public static final EntityType RAVAGER = getById(72);
	public static final EntityType SALMON = getById(73);
	public static final EntityType SHEEP = getById(74);
	public static final EntityType SHULKER = getById(75);
	public static final EntityType SHULKER_BULLET = getById(76);
	public static final EntityType SILVERFISH = getById(77);
	public static final EntityType SKELETON = getById(78);
	public static final EntityType SKELETON_HORSE = getById(79);
	public static final EntityType SLIME = getById(80);
	public static final EntityType SMALL_FIREBALL = getById(81);
	public static final EntityType SNOW_GOLEM = getById(82);
	public static final EntityType SNOWBALL = getById(83);
	public static final EntityType SPECTRAL_ARROW = getById(84);
	public static final EntityType SPIDER = getById(85);
	public static final EntityType SQUID = getById(86);
	public static final EntityType STRAY = getById(87);
	public static final EntityType STRIDER = getById(88);
	public static final EntityType EGG = getById(89);
	public static final EntityType ENDER_PEARL = getById(90);
	public static final EntityType EXPERIENCE_BOTTLE = getById(91);
	public static final EntityType POTION = getById(92);
	public static final EntityType TRIDENT = getById(93);
	public static final EntityType TRADER_LLAMA = getById(94);
	public static final EntityType TROPICAL_FISH = getById(95);
	public static final EntityType TURTLE = getById(96);
	public static final EntityType VEX = getById(97);
	public static final EntityType VILLAGER = getById(98);
	public static final EntityType VINDICATOR = getById(99);
	public static final EntityType WANDERING_TRADER = getById(100);
	public static final EntityType WITCH = getById(101);
	public static final EntityType WITHER = getById(102);
	public static final EntityType WITHER_SKELETON = getById(103);
	public static final EntityType WITHER_SKULL = getById(104);
	public static final EntityType WOLF = getById(105);
	public static final EntityType ZOGLIN = getById(106);
	public static final EntityType ZOMBIE = getById(107);
	public static final EntityType ZOMBIE_HORSE = getById(108);
	public static final EntityType ZOMBIE_VILLAGER = getById(109);
	public static final EntityType ZOMBIFIED_PIGLIN = getById(110);
	public static final EntityType PLAYER = getById(111);
	public static final EntityType FISHING_BOBBER = getById(112);

    // End auto generated code

    /**
     * Gets an entity type by its name
     * @param name Entity name
     * @return EntityType
     */
    public static EntityType getByName(String name) {
        return REGISTRY.stream().filter(entityType -> entityType.getName().equals(name)).collect(CollectorUtil.toSingleton());
    }

    /**
     * Gets an entity by its ID
     * @param id Entity ID
     * @return EntityType
     */
    public static EntityType getById(int id) {
        return REGISTRY.stream().filter(entityType -> entityType.getId() == id).collect(CollectorUtil.toSingleton());
    }

    private final int id;
    private final int internalId;
    private final String name;
    private final String displayName;
    private final double width;
    private final double height;
    private final Entity.Category category;

    public EntityType(
            int id,
            int internalId,
            String name,
            String displayName,
            double width,
            double height,
            Entity.Category category
    ) {
        this.id = id;
        this.internalId = internalId;
        this.name = name;
        this.displayName = displayName;
        this.width = width;
        this.height = height;
        this.category = category;
    }
}