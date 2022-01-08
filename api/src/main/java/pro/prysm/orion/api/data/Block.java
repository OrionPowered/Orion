package pro.prysm.orion.api.data;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
@ToString
public class Block {

    private static final List<Block> REGISTRY = new ArrayList<>(Arrays.asList(
            // Begin auto generated code
            new Block(0, "Air", "air", 0.0, 0.0, 0, 0, 0, true, 0, "default"),
            new Block(1, "Stone", "stone", 1.5, 6, 1, 1, 1, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(2, "Granite", "granite", 1.5, 6, 2, 2, 2, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(3, "Polished Granite", "polished_granite", 1.5, 6, 3, 3, 3, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(4, "Diorite", "diorite", 1.5, 6, 4, 4, 4, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(5, "Polished Diorite", "polished_diorite", 1.5, 6, 5, 5, 5, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(6, "Andesite", "andesite", 1.5, 6, 6, 6, 6, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(7, "Polished Andesite", "polished_andesite", 1.5, 6, 7, 7, 7, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(8, "Grass Block", "grass_block", 0.6, 0.6, 8, 9, 9, true, 0, "mineable/shovel"),
            new Block(9, "Dirt", "dirt", 0.5, 0.5, 10, 10, 10, true, 0, "mineable/shovel"),
            new Block(10, "Coarse Dirt", "coarse_dirt", 0.5, 0.5, 11, 11, 11, true, 0, "mineable/shovel"),
            new Block(11, "Podzol", "podzol", 0.5, 0.5, 12, 13, 13, true, 0, "mineable/shovel"),
            new Block(12, "Cobblestone", "cobblestone", 2, 6, 14, 14, 14, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(13, "Oak Planks", "oak_planks", 2, 3, 15, 15, 15, true, 0, "mineable/axe"),
            new Block(14, "Spruce Planks", "spruce_planks", 2, 3, 16, 16, 16, true, 0, "mineable/axe"),
            new Block(15, "Birch Planks", "birch_planks", 2, 3, 17, 17, 17, true, 0, "mineable/axe"),
            new Block(16, "Jungle Planks", "jungle_planks", 2, 3, 18, 18, 18, true, 0, "mineable/axe"),
            new Block(17, "Acacia Planks", "acacia_planks", 2, 3, 19, 19, 19, true, 0, "mineable/axe"),
            new Block(18, "Dark Oak Planks", "dark_oak_planks", 2, 3, 20, 20, 20, true, 0, "mineable/axe"),
            new Block(19, "Oak Sapling", "oak_sapling", 0.0, 0.0, 21, 22, 21, true, 0, "plant;mineable/axe"),
            new Block(20, "Spruce Sapling", "spruce_sapling", 0.0, 0.0, 23, 24, 23, true, 0, "plant;mineable/axe"),
            new Block(21, "Birch Sapling", "birch_sapling", 0.0, 0.0, 25, 26, 25, true, 0, "plant;mineable/axe"),
            new Block(22, "Jungle Sapling", "jungle_sapling", 0.0, 0.0, 27, 28, 27, true, 0, "plant;mineable/axe"),
            new Block(23, "Acacia Sapling", "acacia_sapling", 0.0, 0.0, 29, 30, 29, true, 0, "plant;mineable/axe"),
            new Block(24, "Dark Oak Sapling", "dark_oak_sapling", 0.0, 0.0, 31, 32, 31, true, 0, "plant;mineable/axe"),
            new Block(25, "Bedrock", "bedrock", 0.0, 3600000, 33, 33, 33, false, 0, "default"),
            new Block(26, "Water", "water", 100, 100, 34, 49, 34, false, 0, "default"),
            new Block(27, "Lava", "lava", 100, 100, 50, 65, 50, false, 15, "default"),
            new Block(28, "Sand", "sand", 0.5, 0.5, 66, 66, 66, true, 0, "mineable/shovel"),
            new Block(29, "Red Sand", "red_sand", 0.5, 0.5, 67, 67, 67, true, 0, "mineable/shovel"),
            new Block(30, "Gravel", "gravel", 0.6, 0.6, 68, 68, 68, true, 0, "mineable/shovel"),
            new Block(31, "Gold Ore", "gold_ore", 3, 3, 69, 69, 69, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(32, "Deepslate Gold Ore", "deepslate_gold_ore", 4.5, 3, 70, 70, 70, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(33, "Iron Ore", "iron_ore", 3, 3, 71, 71, 71, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(34, "Deepslate Iron Ore", "deepslate_iron_ore", 4.5, 3, 72, 72, 72, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(35, "Coal Ore", "coal_ore", 3, 3, 73, 73, 73, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(36, "Deepslate Coal Ore", "deepslate_coal_ore", 4.5, 3, 74, 74, 74, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(37, "Nether Gold Ore", "nether_gold_ore", 3, 3, 75, 75, 75, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(38, "Oak Log", "oak_log", 2, 2, 76, 78, 77, true, 0, "mineable/axe"),
            new Block(39, "Spruce Log", "spruce_log", 2, 2, 79, 81, 80, true, 0, "mineable/axe"),
            new Block(40, "Birch Log", "birch_log", 2, 2, 82, 84, 83, true, 0, "mineable/axe"),
            new Block(41, "Jungle Log", "jungle_log", 2, 2, 85, 87, 86, true, 0, "mineable/axe"),
            new Block(42, "Acacia Log", "acacia_log", 2, 2, 88, 90, 89, true, 0, "mineable/axe"),
            new Block(43, "Dark Oak Log", "dark_oak_log", 2, 2, 91, 93, 92, true, 0, "mineable/axe"),
            new Block(44, "Stripped Spruce Log", "stripped_spruce_log", 2, 2, 94, 96, 95, true, 0, "mineable/axe"),
            new Block(45, "Stripped Birch Log", "stripped_birch_log", 2, 2, 97, 99, 98, true, 0, "mineable/axe"),
            new Block(46, "Stripped Jungle Log", "stripped_jungle_log", 2, 2, 100, 102, 101, true, 0, "mineable/axe"),
            new Block(47, "Stripped Acacia Log", "stripped_acacia_log", 2, 2, 103, 105, 104, true, 0, "mineable/axe"),
            new Block(48, "Stripped Dark Oak Log", "stripped_dark_oak_log", 2, 2, 106, 108, 107, true, 0, "mineable/axe"),
            new Block(49, "Stripped Oak Log", "stripped_oak_log", 2, 2, 109, 111, 110, true, 0, "mineable/axe"),
            new Block(50, "Oak Wood", "oak_wood", 2, 2, 112, 114, 113, true, 0, "mineable/axe"),
            new Block(51, "Spruce Wood", "spruce_wood", 2, 2, 115, 117, 116, true, 0, "mineable/axe"),
            new Block(52, "Birch Wood", "birch_wood", 2, 2, 118, 120, 119, true, 0, "mineable/axe"),
            new Block(53, "Jungle Wood", "jungle_wood", 2, 2, 121, 123, 122, true, 0, "mineable/axe"),
            new Block(54, "Acacia Wood", "acacia_wood", 2, 2, 124, 126, 125, true, 0, "mineable/axe"),
            new Block(55, "Dark Oak Wood", "dark_oak_wood", 2, 2, 127, 129, 128, true, 0, "mineable/axe"),
            new Block(56, "Stripped Oak Wood", "stripped_oak_wood", 2, 2, 130, 132, 131, true, 0, "mineable/axe"),
            new Block(57, "Stripped Spruce Wood", "stripped_spruce_wood", 2, 2, 133, 135, 134, true, 0, "mineable/axe"),
            new Block(58, "Stripped Birch Wood", "stripped_birch_wood", 2, 2, 136, 138, 137, true, 0, "mineable/axe"),
            new Block(59, "Stripped Jungle Wood", "stripped_jungle_wood", 2, 2, 139, 141, 140, true, 0, "mineable/axe"),
            new Block(60, "Stripped Acacia Wood", "stripped_acacia_wood", 2, 2, 142, 144, 143, true, 0, "mineable/axe"),
            new Block(61, "Stripped Dark Oak Wood", "stripped_dark_oak_wood", 2, 2, 145, 147, 146, true, 0, "mineable/axe"),
            new Block(62, "Oak Leaves", "oak_leaves", 0.2, 0.2, 148, 161, 161, true, 0, "leaves;mineable/hoe"),
            new Block(63, "Spruce Leaves", "spruce_leaves", 0.2, 0.2, 162, 175, 175, true, 0, "leaves;mineable/hoe"),
            new Block(64, "Birch Leaves", "birch_leaves", 0.2, 0.2, 176, 189, 189, true, 0, "leaves;mineable/hoe"),
            new Block(65, "Jungle Leaves", "jungle_leaves", 0.2, 0.2, 190, 203, 203, true, 0, "leaves;mineable/hoe"),
            new Block(66, "Acacia Leaves", "acacia_leaves", 0.2, 0.2, 204, 217, 217, true, 0, "leaves;mineable/hoe"),
            new Block(67, "Dark Oak Leaves", "dark_oak_leaves", 0.2, 0.2, 218, 231, 231, true, 0, "leaves;mineable/hoe"),
            new Block(68, "Azalea Leaves", "azalea_leaves", 0.2, 0.2, 232, 245, 245, true, 0, "leaves;mineable/hoe"),
            new Block(69, "Flowering Azalea Leaves", "flowering_azalea_leaves", 0.2, 0.2, 246, 259, 259, true, 0, "leaves;mineable/axe;mineable/hoe"),
            new Block(70, "Sponge", "sponge", 0.6, 0.6, 260, 260, 260, true, 0, "mineable/hoe"),
            new Block(71, "Wet Sponge", "wet_sponge", 0.6, 0.6, 261, 261, 261, true, 0, "mineable/hoe"),
            new Block(72, "Glass", "glass", 0.3, 0.3, 262, 262, 262, true, 0, "default"),
            new Block(73, "Lapis Lazuli Ore", "lapis_ore", 3, 3, 263, 263, 263, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(74, "Deepslate Lapis Lazuli Ore", "deepslate_lapis_ore", 4.5, 3, 264, 264, 264, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(75, "Block of Lapis Lazuli", "lapis_block", 3, 3, 265, 265, 265, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(76, "Dispenser", "dispenser", 3.5, 3.5, 266, 277, 267, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(77, "Sandstone", "sandstone", 0.8, 0.8, 278, 278, 278, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(78, "Chiseled Sandstone", "chiseled_sandstone", 0.8, 0.8, 279, 279, 279, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(79, "Cut Sandstone", "cut_sandstone", 0.8, 0.8, 280, 280, 280, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(80, "Note Block", "note_block", 0.8, 0.8, 281, 1080, 282, true, 0, "mineable/axe"),
            new Block(81, "White Bed", "white_bed", 0.2, 0.2, 1081, 1096, 1084, true, 0, "default"),
            new Block(82, "Orange Bed", "orange_bed", 0.2, 0.2, 1097, 1112, 1100, true, 0, "default"),
            new Block(83, "Magenta Bed", "magenta_bed", 0.2, 0.2, 1113, 1128, 1116, true, 0, "default"),
            new Block(84, "Light Blue Bed", "light_blue_bed", 0.2, 0.2, 1129, 1144, 1132, true, 0, "default"),
            new Block(85, "Yellow Bed", "yellow_bed", 0.2, 0.2, 1145, 1160, 1148, true, 0, "default"),
            new Block(86, "Lime Bed", "lime_bed", 0.2, 0.2, 1161, 1176, 1164, true, 0, "default"),
            new Block(87, "Pink Bed", "pink_bed", 0.2, 0.2, 1177, 1192, 1180, true, 0, "default"),
            new Block(88, "Gray Bed", "gray_bed", 0.2, 0.2, 1193, 1208, 1196, true, 0, "default"),
            new Block(89, "Light Gray Bed", "light_gray_bed", 0.2, 0.2, 1209, 1224, 1212, true, 0, "default"),
            new Block(90, "Cyan Bed", "cyan_bed", 0.2, 0.2, 1225, 1240, 1228, true, 0, "default"),
            new Block(91, "Purple Bed", "purple_bed", 0.2, 0.2, 1241, 1256, 1244, true, 0, "default"),
            new Block(92, "Blue Bed", "blue_bed", 0.2, 0.2, 1257, 1272, 1260, true, 0, "default"),
            new Block(93, "Brown Bed", "brown_bed", 0.2, 0.2, 1273, 1288, 1276, true, 0, "default"),
            new Block(94, "Green Bed", "green_bed", 0.2, 0.2, 1289, 1304, 1292, true, 0, "default"),
            new Block(95, "Red Bed", "red_bed", 0.2, 0.2, 1305, 1320, 1308, true, 0, "default"),
            new Block(96, "Black Bed", "black_bed", 0.2, 0.2, 1321, 1336, 1324, true, 0, "default"),
            new Block(97, "Powered Rail", "powered_rail", 0.7, 0.7, 1337, 1360, 1350, true, 0, "mineable/pickaxe"),
            new Block(98, "Detector Rail", "detector_rail", 0.7, 0.7, 1361, 1384, 1374, true, 0, "mineable/pickaxe"),
            new Block(99, "Sticky Piston", "sticky_piston", 1.5, 1.5, 1385, 1396, 1391, true, 0, "mineable/pickaxe"),
            new Block(100, "Cobweb", "cobweb", 4, 4, 1397, 1397, 1397, true, 0, "coweb", new int[]{699, 704, 709, 714, 719, 724, 84}),
            new Block(101, "Grass", "grass", 0.0, 0.0, 1398, 1398, 1398, true, 0, "plant;mineable/axe"),
            new Block(102, "Fern", "fern", 0.0, 0.0, 1399, 1399, 1399, true, 0, "plant;mineable/axe"),
            new Block(103, "Dead Bush", "dead_bush", 0.0, 0.0, 1400, 1400, 1400, true, 0, "plant;mineable/axe"),
            new Block(104, "Seagrass", "seagrass", 0.0, 0.0, 1401, 1401, 1401, true, 0, "default"),
            new Block(105, "Tall Seagrass", "tall_seagrass", 0.0, 0.0, 1402, 1403, 1403, true, 0, "default"),
            new Block(106, "Piston", "piston", 1.5, 1.5, 1404, 1415, 1410, true, 0, "mineable/pickaxe"),
            new Block(107, "Piston Head", "piston_head", 1.5, 1.5, 1416, 1439, 1418, true, 0, "mineable/pickaxe"),
            new Block(108, "White Wool", "white_wool", 0.8, 0.8, 1440, 1440, 1440, true, 0, "wool"),
            new Block(109, "Orange Wool", "orange_wool", 0.8, 0.8, 1441, 1441, 1441, true, 0, "wool"),
            new Block(110, "Magenta Wool", "magenta_wool", 0.8, 0.8, 1442, 1442, 1442, true, 0, "wool"),
            new Block(111, "Light Blue Wool", "light_blue_wool", 0.8, 0.8, 1443, 1443, 1443, true, 0, "wool"),
            new Block(112, "Yellow Wool", "yellow_wool", 0.8, 0.8, 1444, 1444, 1444, true, 0, "wool"),
            new Block(113, "Lime Wool", "lime_wool", 0.8, 0.8, 1445, 1445, 1445, true, 0, "wool"),
            new Block(114, "Pink Wool", "pink_wool", 0.8, 0.8, 1446, 1446, 1446, true, 0, "wool"),
            new Block(115, "Gray Wool", "gray_wool", 0.8, 0.8, 1447, 1447, 1447, true, 0, "wool"),
            new Block(116, "Light Gray Wool", "light_gray_wool", 0.8, 0.8, 1448, 1448, 1448, true, 0, "wool"),
            new Block(117, "Cyan Wool", "cyan_wool", 0.8, 0.8, 1449, 1449, 1449, true, 0, "wool"),
            new Block(118, "Purple Wool", "purple_wool", 0.8, 0.8, 1450, 1450, 1450, true, 0, "wool"),
            new Block(119, "Blue Wool", "blue_wool", 0.8, 0.8, 1451, 1451, 1451, true, 0, "wool"),
            new Block(120, "Brown Wool", "brown_wool", 0.8, 0.8, 1452, 1452, 1452, true, 0, "wool"),
            new Block(121, "Green Wool", "green_wool", 0.8, 0.8, 1453, 1453, 1453, true, 0, "wool"),
            new Block(122, "Red Wool", "red_wool", 0.8, 0.8, 1454, 1454, 1454, true, 0, "wool"),
            new Block(123, "Black Wool", "black_wool", 0.8, 0.8, 1455, 1455, 1455, true, 0, "wool"),
            new Block(124, "Moving Piston", "moving_piston", 0.0, -1, 1456, 1467, 1456, false, 0, "default"),
            new Block(125, "Dandelion", "dandelion", 0.0, 0.0, 1468, 1468, 1468, true, 0, "plant;mineable/axe"),
            new Block(126, "Poppy", "poppy", 0.0, 0.0, 1469, 1469, 1469, true, 0, "plant;mineable/axe"),
            new Block(127, "Blue Orchid", "blue_orchid", 0.0, 0.0, 1470, 1470, 1470, true, 0, "plant;mineable/axe"),
            new Block(128, "Allium", "allium", 0.0, 0.0, 1471, 1471, 1471, true, 0, "plant;mineable/axe"),
            new Block(129, "Azure Bluet", "azure_bluet", 0.0, 0.0, 1472, 1472, 1472, true, 0, "plant;mineable/axe"),
            new Block(130, "Red Tulip", "red_tulip", 0.0, 0.0, 1473, 1473, 1473, true, 0, "plant;mineable/axe"),
            new Block(131, "Orange Tulip", "orange_tulip", 0.0, 0.0, 1474, 1474, 1474, true, 0, "plant;mineable/axe"),
            new Block(132, "White Tulip", "white_tulip", 0.0, 0.0, 1475, 1475, 1475, true, 0, "plant;mineable/axe"),
            new Block(133, "Pink Tulip", "pink_tulip", 0.0, 0.0, 1476, 1476, 1476, true, 0, "plant;mineable/axe"),
            new Block(134, "Oxeye Daisy", "oxeye_daisy", 0.0, 0.0, 1477, 1477, 1477, true, 0, "plant;mineable/axe"),
            new Block(135, "Cornflower", "cornflower", 0.0, 0.0, 1478, 1478, 1478, true, 0, "plant;mineable/axe"),
            new Block(136, "Wither Rose", "wither_rose", 0.0, 0.0, 1479, 1479, 1479, true, 0, "plant;mineable/axe"),
            new Block(137, "Lily of the Valley", "lily_of_the_valley", 0.0, 0.0, 1480, 1480, 1480, true, 0, "plant;mineable/axe"),
            new Block(138, "Brown Mushroom", "brown_mushroom", 0.0, 0.0, 1481, 1481, 1481, true, 1, "plant;mineable/axe"),
            new Block(139, "Red Mushroom", "red_mushroom", 0.0, 0.0, 1482, 1482, 1482, true, 0, "plant;mineable/axe"),
            new Block(140, "Block of Gold", "gold_block", 3, 6, 1483, 1483, 1483, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(141, "Block of Iron", "iron_block", 5, 6, 1484, 1484, 1484, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(142, "Bricks", "bricks", 2, 6, 1485, 1485, 1485, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(143, "TNT", "tnt", 0.0, 0.0, 1486, 1487, 1487, true, 0, "default"),
            new Block(144, "Bookshelf", "bookshelf", 1.5, 1.5, 1488, 1488, 1488, true, 0, "mineable/axe"),
            new Block(145, "Mossy Cobblestone", "mossy_cobblestone", 2, 6, 1489, 1489, 1489, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(146, "Obsidian", "obsidian", 50, 1200, 1490, 1490, 1490, true, 0, "mineable/pickaxe", new int[]{721, 72}),
            new Block(147, "Torch", "torch", 0.0, 0.0, 1491, 1491, 1491, true, 14, "default"),
            new Block(148, "Wall Torch", "wall_torch", 0.0, 0.0, 1492, 1495, 1492, true, 14, "default"),
            new Block(149, "Fire", "fire", 0.0, 0.0, 1496, 2007, 1527, true, 15, "default"),
            new Block(150, "Soul Fire", "soul_fire", 0.0, 0.0, 2008, 2008, 2008, true, 10, "default"),
            new Block(151, "Spawner", "spawner", 5, 5, 2009, 2009, 2009, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(152, "Oak Stairs", "oak_stairs", 2, 3, 2010, 2089, 2021, true, 0, "mineable/axe"),
            new Block(153, "Chest", "chest", 2.5, 2.5, 2090, 2113, 2091, true, 0, "mineable/axe"),
            new Block(154, "Redstone Wire", "redstone_wire", 0.0, 0.0, 2114, 3409, 3274, true, 0, "default"),
            new Block(155, "Diamond Ore", "diamond_ore", 3, 3, 3410, 3410, 3410, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(156, "Deepslate Diamond Ore", "deepslate_diamond_ore", 4.5, 3, 3411, 3411, 3411, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(157, "Block of Diamond", "diamond_block", 5, 6, 3412, 3412, 3412, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(158, "Crafting Table", "crafting_table", 2.5, 2.5, 3413, 3413, 3413, true, 0, "mineable/axe"),
            new Block(159, "Wheat Crops", "wheat", 0.0, 0.0, 3414, 3421, 3414, true, 0, "plant;mineable/axe"),
            new Block(160, "Farmland", "farmland", 0.6, 0.6, 3422, 3429, 3422, true, 0, "mineable/shovel"),
            new Block(161, "Furnace", "furnace", 3.5, 3.5, 3430, 3437, 3431, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(162, "Oak Sign", "oak_sign", 1, 1, 3438, 3469, 3439, true, 0, "mineable/axe"),
            new Block(163, "Spruce Sign", "spruce_sign", 1, 1, 3470, 3501, 3471, true, 0, "mineable/axe"),
            new Block(164, "Birch Sign", "birch_sign", 1, 1, 3502, 3533, 3503, true, 0, "mineable/axe"),
            new Block(165, "Acacia Sign", "acacia_sign", 1, 1, 3534, 3565, 3535, true, 0, "mineable/axe"),
            new Block(166, "Jungle Sign", "jungle_sign", 1, 1, 3566, 3597, 3567, true, 0, "mineable/axe"),
            new Block(167, "Dark Oak Sign", "dark_oak_sign", 1, 1, 3598, 3629, 3599, true, 0, "mineable/axe"),
            new Block(168, "Oak Door", "oak_door", 3, 3, 3630, 3693, 3641, true, 0, "mineable/axe"),
            new Block(169, "Ladder", "ladder", 0.4, 0.4, 3694, 3701, 3695, true, 0, "mineable/axe"),
            new Block(170, "Rail", "rail", 0.7, 0.7, 3702, 3721, 3703, true, 0, "mineable/pickaxe"),
            new Block(171, "Cobblestone Stairs", "cobblestone_stairs", 2, 6, 3722, 3801, 3733, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(172, "Oak Wall Sign", "oak_wall_sign", 1, 1, 3802, 3809, 3803, true, 0, "mineable/axe"),
            new Block(173, "Spruce Wall Sign", "spruce_wall_sign", 1, 1, 3810, 3817, 3811, true, 0, "mineable/axe"),
            new Block(174, "Birch Wall Sign", "birch_wall_sign", 1, 1, 3818, 3825, 3819, true, 0, "mineable/axe"),
            new Block(175, "Acacia Wall Sign", "acacia_wall_sign", 1, 1, 3826, 3833, 3827, true, 0, "mineable/axe"),
            new Block(176, "Jungle Wall Sign", "jungle_wall_sign", 1, 1, 3834, 3841, 3835, true, 0, "mineable/axe"),
            new Block(177, "Dark Oak Wall Sign", "dark_oak_wall_sign", 1, 1, 3842, 3849, 3843, true, 0, "mineable/axe"),
            new Block(178, "Lever", "lever", 0.5, 0.5, 3850, 3873, 3859, true, 0, "default"),
            new Block(179, "Stone Pressure Plate", "stone_pressure_plate", 0.5, 0.5, 3874, 3875, 3875, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(180, "Iron Door", "iron_door", 5, 5, 3876, 3939, 3887, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(181, "Oak Pressure Plate", "oak_pressure_plate", 0.5, 0.5, 3940, 3941, 3941, true, 0, "mineable/axe"),
            new Block(182, "Spruce Pressure Plate", "spruce_pressure_plate", 0.5, 0.5, 3942, 3943, 3943, true, 0, "mineable/axe"),
            new Block(183, "Birch Pressure Plate", "birch_pressure_plate", 0.5, 0.5, 3944, 3945, 3945, true, 0, "mineable/axe"),
            new Block(184, "Jungle Pressure Plate", "jungle_pressure_plate", 0.5, 0.5, 3946, 3947, 3947, true, 0, "mineable/axe"),
            new Block(185, "Acacia Pressure Plate", "acacia_pressure_plate", 0.5, 0.5, 3948, 3949, 3949, true, 0, "mineable/axe"),
            new Block(186, "Dark Oak Pressure Plate", "dark_oak_pressure_plate", 0.5, 0.5, 3950, 3951, 3951, true, 0, "mineable/axe"),
            new Block(187, "Redstone Ore", "redstone_ore", 3, 3, 3952, 3953, 3953, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(188, "Deepslate Redstone Ore", "deepslate_redstone_ore", 4.5, 3, 3954, 3955, 3955, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(189, "Redstone Torch", "redstone_torch", 0.0, 0.0, 3956, 3957, 3956, true, 7, "default"),
            new Block(190, "Redstone Wall Torch", "redstone_wall_torch", 0.0, 0.0, 3958, 3965, 3958, true, 7, "default"),
            new Block(191, "Stone Button", "stone_button", 0.5, 0.5, 3966, 3989, 3975, true, 0, "mineable/pickaxe"),
            new Block(192, "Snow", "snow", 0.1, 0.1, 3990, 3997, 3990, true, 0, "mineable/shovel", new int[]{700, 705, 710, 715, 720, 72}),
            new Block(193, "Ice", "ice", 0.5, 0.5, 3998, 3998, 3998, true, 0, "mineable/pickaxe"),
            new Block(194, "Snow Block", "snow_block", 0.2, 0.2, 3999, 3999, 3999, true, 0, "mineable/shovel", new int[]{700, 705, 710, 715, 720, 72}),
            new Block(195, "Cactus", "cactus", 0.4, 0.4, 4000, 4015, 4000, true, 0, "default"),
            new Block(196, "Clay", "clay", 0.6, 0.6, 4016, 4016, 4016, true, 0, "mineable/shovel"),
            new Block(197, "Sugar Cane", "sugar_cane", 0.0, 0.0, 4017, 4032, 4017, true, 0, "plant;mineable/axe"),
            new Block(198, "Jukebox", "jukebox", 2, 6, 4033, 4034, 4034, true, 0, "mineable/axe"),
            new Block(199, "Oak Fence", "oak_fence", 2, 3, 4035, 4066, 4066, true, 0, "mineable/axe"),
            new Block(200, "Pumpkin", "pumpkin", 1, 1, 4067, 4067, 4067, true, 0, "gourd;mineable/axe"),
            new Block(201, "Netherrack", "netherrack", 0.4, 0.4, 4068, 4068, 4068, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(202, "Soul Sand", "soul_sand", 0.5, 0.5, 4069, 4069, 4069, true, 0, "mineable/shovel"),
            new Block(203, "Soul Soil", "soul_soil", 0.5, 0.5, 4070, 4070, 4070, true, 0, "mineable/shovel"),
            new Block(204, "Basalt", "basalt", 1.25, 4.2, 4071, 4073, 4072, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(205, "Polished Basalt", "polished_basalt", 1.25, 4.2, 4074, 4076, 4075, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(206, "Soul Torch", "soul_torch", 0.0, 0.0, 4077, 4077, 4077, true, 10, "default"),
            new Block(207, "Soul Wall Torch", "soul_wall_torch", 0.0, 0.0, 4078, 4081, 4078, true, 10, "default"),
            new Block(208, "Glowstone", "glowstone", 0.3, 0.3, 4082, 4082, 4082, true, 15, "default"),
            new Block(209, "Nether Portal", "nether_portal", 0.0, -1, 4083, 4084, 4083, false, 11, "default"),
            new Block(210, "Carved Pumpkin", "carved_pumpkin", 1, 1, 4085, 4088, 4085, true, 0, "gourd;mineable/axe"),
            new Block(211, "Jack o'Lantern", "jack_o_lantern", 1, 1, 4089, 4092, 4089, true, 15, "gourd;mineable/axe"),
            new Block(212, "Cake", "cake", 0.5, 0.5, 4093, 4099, 4093, true, 0, "default"),
            new Block(213, "Redstone Repeater", "repeater", 0.0, 0.0, 4100, 4163, 4103, true, 0, "default"),
            new Block(214, "White Stained Glass", "white_stained_glass", 0.3, 0.3, 4164, 4164, 4164, true, 0, "default"),
            new Block(215, "Orange Stained Glass", "orange_stained_glass", 0.3, 0.3, 4165, 4165, 4165, true, 0, "default"),
            new Block(216, "Magenta Stained Glass", "magenta_stained_glass", 0.3, 0.3, 4166, 4166, 4166, true, 0, "default"),
            new Block(217, "Light Blue Stained Glass", "light_blue_stained_glass", 0.3, 0.3, 4167, 4167, 4167, true, 0, "default"),
            new Block(218, "Yellow Stained Glass", "yellow_stained_glass", 0.3, 0.3, 4168, 4168, 4168, true, 0, "default"),
            new Block(219, "Lime Stained Glass", "lime_stained_glass", 0.3, 0.3, 4169, 4169, 4169, true, 0, "default"),
            new Block(220, "Pink Stained Glass", "pink_stained_glass", 0.3, 0.3, 4170, 4170, 4170, true, 0, "default"),
            new Block(221, "Gray Stained Glass", "gray_stained_glass", 0.3, 0.3, 4171, 4171, 4171, true, 0, "default"),
            new Block(222, "Light Gray Stained Glass", "light_gray_stained_glass", 0.3, 0.3, 4172, 4172, 4172, true, 0, "default"),
            new Block(223, "Cyan Stained Glass", "cyan_stained_glass", 0.3, 0.3, 4173, 4173, 4173, true, 0, "default"),
            new Block(224, "Purple Stained Glass", "purple_stained_glass", 0.3, 0.3, 4174, 4174, 4174, true, 0, "default"),
            new Block(225, "Blue Stained Glass", "blue_stained_glass", 0.3, 0.3, 4175, 4175, 4175, true, 0, "default"),
            new Block(226, "Brown Stained Glass", "brown_stained_glass", 0.3, 0.3, 4176, 4176, 4176, true, 0, "default"),
            new Block(227, "Green Stained Glass", "green_stained_glass", 0.3, 0.3, 4177, 4177, 4177, true, 0, "default"),
            new Block(228, "Red Stained Glass", "red_stained_glass", 0.3, 0.3, 4178, 4178, 4178, true, 0, "default"),
            new Block(229, "Black Stained Glass", "black_stained_glass", 0.3, 0.3, 4179, 4179, 4179, true, 0, "default"),
            new Block(230, "Oak Trapdoor", "oak_trapdoor", 3, 3, 4180, 4243, 4195, true, 0, "mineable/axe"),
            new Block(231, "Spruce Trapdoor", "spruce_trapdoor", 3, 3, 4244, 4307, 4259, true, 0, "mineable/axe"),
            new Block(232, "Birch Trapdoor", "birch_trapdoor", 3, 3, 4308, 4371, 4323, true, 0, "mineable/axe"),
            new Block(233, "Jungle Trapdoor", "jungle_trapdoor", 3, 3, 4372, 4435, 4387, true, 0, "mineable/axe"),
            new Block(234, "Acacia Trapdoor", "acacia_trapdoor", 3, 3, 4436, 4499, 4451, true, 0, "mineable/axe"),
            new Block(235, "Dark Oak Trapdoor", "dark_oak_trapdoor", 3, 3, 4500, 4563, 4515, true, 0, "mineable/axe"),
            new Block(236, "Stone Bricks", "stone_bricks", 1.5, 6, 4564, 4564, 4564, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(237, "Mossy Stone Bricks", "mossy_stone_bricks", 1.5, 6, 4565, 4565, 4565, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(238, "Cracked Stone Bricks", "cracked_stone_bricks", 1.5, 6, 4566, 4566, 4566, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(239, "Chiseled Stone Bricks", "chiseled_stone_bricks", 1.5, 6, 4567, 4567, 4567, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(240, "Infested Stone", "infested_stone", 0.0, 0.0, 4568, 4568, 4568, true, 0, "mineable/pickaxe"),
            new Block(241, "Infested Cobblestone", "infested_cobblestone", 0.0, 0.0, 4569, 4569, 4569, true, 0, "mineable/pickaxe"),
            new Block(242, "Infested Stone Bricks", "infested_stone_bricks", 0.0, 0.0, 4570, 4570, 4570, true, 0, "mineable/pickaxe"),
            new Block(243, "Infested Mossy Stone Bricks", "infested_mossy_stone_bricks", 0.0, 0.0, 4571, 4571, 4571, true, 0, "mineable/pickaxe"),
            new Block(244, "Infested Cracked Stone Bricks", "infested_cracked_stone_bricks", 0.0, 0.0, 4572, 4572, 4572, true, 0, "mineable/pickaxe"),
            new Block(245, "Infested Chiseled Stone Bricks", "infested_chiseled_stone_bricks", 0.0, 0.0, 4573, 4573, 4573, true, 0, "mineable/pickaxe"),
            new Block(246, "Brown Mushroom Block", "brown_mushroom_block", 0.2, 0.2, 4574, 4637, 4574, true, 0, "mineable/axe"),
            new Block(247, "Red Mushroom Block", "red_mushroom_block", 0.2, 0.2, 4638, 4701, 4638, true, 0, "mineable/axe"),
            new Block(248, "Mushroom Stem", "mushroom_stem", 0.2, 0.2, 4702, 4765, 4702, true, 0, "mineable/axe"),
            new Block(249, "Iron Bars", "iron_bars", 5, 6, 4766, 4797, 4797, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(250, "Chain", "chain", 5, 6, 4798, 4803, 4801, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(251, "Glass Pane", "glass_pane", 0.3, 0.3, 4804, 4835, 4835, true, 0, "default"),
            new Block(252, "Melon", "melon", 1, 1, 4836, 4836, 4836, true, 0, "gourd;mineable/axe"),
            new Block(253, "Attached Pumpkin Stem", "attached_pumpkin_stem", 0.0, 0.0, 4837, 4840, 4837, true, 0, "plant;mineable/axe"),
            new Block(254, "Attached Melon Stem", "attached_melon_stem", 0.0, 0.0, 4841, 4844, 4841, true, 0, "plant;mineable/axe"),
            new Block(255, "Pumpkin Stem", "pumpkin_stem", 0.0, 0.0, 4845, 4852, 4845, true, 0, "plant;mineable/axe"),
            new Block(256, "Melon Stem", "melon_stem", 0.0, 0.0, 4853, 4860, 4853, true, 0, "plant;mineable/axe"),
            new Block(257, "Vines", "vine", 0.2, 0.2, 4861, 4892, 4892, true, 0, "vine_or_glow_lichen;plant;mineable/axe"),
            new Block(258, "Glow Lichen", "glow_lichen", 0.2, 0.2, 4893, 5020, 5020, true, 0, "vine_or_glow_lichen;plant;mineable/axe"),
            new Block(259, "Oak Fence Gate", "oak_fence_gate", 2, 3, 5021, 5052, 5028, true, 0, "mineable/axe"),
            new Block(260, "Brick Stairs", "brick_stairs", 2, 6, 5053, 5132, 5064, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(261, "Stone Brick Stairs", "stone_brick_stairs", 1.5, 6, 5133, 5212, 5144, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(262, "Mycelium", "mycelium", 0.6, 0.6, 5213, 5214, 5214, true, 0, "mineable/shovel"),
            new Block(263, "Lily Pad", "lily_pad", 0.0, 0.0, 5215, 5215, 5215, true, 0, "plant;mineable/axe"),
            new Block(264, "Nether Bricks", "nether_bricks", 2, 6, 5216, 5216, 5216, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(265, "Nether Brick Fence", "nether_brick_fence", 2, 6, 5217, 5248, 5248, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(266, "Nether Brick Stairs", "nether_brick_stairs", 2, 6, 5249, 5328, 5260, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(267, "Nether Wart", "nether_wart", 0.0, 0.0, 5329, 5332, 5329, true, 0, "plant;mineable/axe"),
            new Block(268, "Enchanting Table", "enchanting_table", 5, 1200, 5333, 5333, 5333, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(269, "Brewing Stand", "brewing_stand", 0.5, 0.5, 5334, 5341, 5341, true, 1, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(270, "Cauldron", "cauldron", 2, 2, 5342, 5342, 5342, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(271, "Water Cauldron", "water_cauldron", 0.0, 0.0, 5343, 5345, 5343, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(272, "Lava Cauldron", "lava_cauldron", 0.0, 0.0, 5346, 5346, 5346, true, 15, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(273, "Powder Snow Cauldron", "powder_snow_cauldron", 0.0, 0.0, 5347, 5349, 5347, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(274, "End Portal", "end_portal", 0.0, 3600000, 5350, 5350, 5350, false, 15, "default"),
            new Block(275, "End Portal Frame", "end_portal_frame", 0.0, 3600000, 5351, 5358, 5355, false, 1, "default"),
            new Block(276, "End Stone", "end_stone", 3, 9, 5359, 5359, 5359, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(277, "Dragon Egg", "dragon_egg", 3, 9, 5360, 5360, 5360, true, 1, "default"),
            new Block(278, "Redstone Lamp", "redstone_lamp", 0.3, 0.3, 5361, 5362, 5362, true, 0, "default"),
            new Block(279, "Cocoa", "cocoa", 0.2, 3, 5363, 5374, 5363, true, 0, "plant;mineable/axe"),
            new Block(280, "Sandstone Stairs", "sandstone_stairs", 0.8, 0.8, 5375, 5454, 5386, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(281, "Emerald Ore", "emerald_ore", 3, 3, 5455, 5455, 5455, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(282, "Deepslate Emerald Ore", "deepslate_emerald_ore", 4.5, 3, 5456, 5456, 5456, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(283, "Ender Chest", "ender_chest", 22.5, 600, 5457, 5464, 5458, true, 7, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(284, "Tripwire Hook", "tripwire_hook", 0.0, 0.0, 5465, 5480, 5474, true, 0, "default"),
            new Block(285, "Tripwire", "tripwire", 0.0, 0.0, 5481, 5608, 5608, true, 0, "default"),
            new Block(286, "Block of Emerald", "emerald_block", 5, 6, 5609, 5609, 5609, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(287, "Spruce Stairs", "spruce_stairs", 2, 3, 5610, 5689, 5621, true, 0, "mineable/axe"),
            new Block(288, "Birch Stairs", "birch_stairs", 2, 3, 5690, 5769, 5701, true, 0, "mineable/axe"),
            new Block(289, "Jungle Stairs", "jungle_stairs", 2, 3, 5770, 5849, 5781, true, 0, "mineable/axe"),
            new Block(290, "Command Block", "command_block", 0.0, 3600000, 5850, 5861, 5856, false, 0, "default", new int[]{}),
            new Block(291, "Beacon", "beacon", 3, 3, 5862, 5862, 5862, true, 15, "default"),
            new Block(292, "Cobblestone Wall", "cobblestone_wall", 2, 6, 5863, 6186, 5866, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(293, "Mossy Cobblestone Wall", "mossy_cobblestone_wall", 2, 6, 6187, 6510, 6190, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(294, "Flower Pot", "flower_pot", 0.0, 0.0, 6511, 6511, 6511, true, 0, "default"),
            new Block(295, "Potted Oak Sapling", "potted_oak_sapling", 0.0, 0.0, 6512, 6512, 6512, true, 0, "default"),
            new Block(296, "Potted Spruce Sapling", "potted_spruce_sapling", 0.0, 0.0, 6513, 6513, 6513, true, 0, "default"),
            new Block(297, "Potted Birch Sapling", "potted_birch_sapling", 0.0, 0.0, 6514, 6514, 6514, true, 0, "default"),
            new Block(298, "Potted Jungle Sapling", "potted_jungle_sapling", 0.0, 0.0, 6515, 6515, 6515, true, 0, "default"),
            new Block(299, "Potted Acacia Sapling", "potted_acacia_sapling", 0.0, 0.0, 6516, 6516, 6516, true, 0, "default"),
            new Block(300, "Potted Dark Oak Sapling", "potted_dark_oak_sapling", 0.0, 0.0, 6517, 6517, 6517, true, 0, "default"),
            new Block(301, "Potted Fern", "potted_fern", 0.0, 0.0, 6518, 6518, 6518, true, 0, "default"),
            new Block(302, "Potted Dandelion", "potted_dandelion", 0.0, 0.0, 6519, 6519, 6519, true, 0, "default"),
            new Block(303, "Potted Poppy", "potted_poppy", 0.0, 0.0, 6520, 6520, 6520, true, 0, "default"),
            new Block(304, "Potted Blue Orchid", "potted_blue_orchid", 0.0, 0.0, 6521, 6521, 6521, true, 0, "default"),
            new Block(305, "Potted Allium", "potted_allium", 0.0, 0.0, 6522, 6522, 6522, true, 0, "default"),
            new Block(306, "Potted Azure Bluet", "potted_azure_bluet", 0.0, 0.0, 6523, 6523, 6523, true, 0, "default"),
            new Block(307, "Potted Red Tulip", "potted_red_tulip", 0.0, 0.0, 6524, 6524, 6524, true, 0, "default"),
            new Block(308, "Potted Orange Tulip", "potted_orange_tulip", 0.0, 0.0, 6525, 6525, 6525, true, 0, "default"),
            new Block(309, "Potted White Tulip", "potted_white_tulip", 0.0, 0.0, 6526, 6526, 6526, true, 0, "default"),
            new Block(310, "Potted Pink Tulip", "potted_pink_tulip", 0.0, 0.0, 6527, 6527, 6527, true, 0, "default"),
            new Block(311, "Potted Oxeye Daisy", "potted_oxeye_daisy", 0.0, 0.0, 6528, 6528, 6528, true, 0, "default"),
            new Block(312, "Potted Cornflower", "potted_cornflower", 0.0, 0.0, 6529, 6529, 6529, true, 0, "default"),
            new Block(313, "Potted Lily of the Valley", "potted_lily_of_the_valley", 0.0, 0.0, 6530, 6530, 6530, true, 0, "default"),
            new Block(314, "Potted Wither Rose", "potted_wither_rose", 0.0, 0.0, 6531, 6531, 6531, true, 0, "default"),
            new Block(315, "Potted Red Mushroom", "potted_red_mushroom", 0.0, 0.0, 6532, 6532, 6532, true, 0, "default"),
            new Block(316, "Potted Brown Mushroom", "potted_brown_mushroom", 0.0, 0.0, 6533, 6533, 6533, true, 0, "default"),
            new Block(317, "Potted Dead Bush", "potted_dead_bush", 0.0, 0.0, 6534, 6534, 6534, true, 0, "default"),
            new Block(318, "Potted Cactus", "potted_cactus", 0.0, 0.0, 6535, 6535, 6535, true, 0, "default"),
            new Block(319, "Carrots", "carrots", 0.0, 0.0, 6536, 6543, 6536, true, 0, "plant;mineable/axe"),
            new Block(320, "Potatoes", "potatoes", 0.0, 0.0, 6544, 6551, 6544, true, 0, "plant;mineable/axe"),
            new Block(321, "Oak Button", "oak_button", 0.5, 0.5, 6552, 6575, 6561, true, 0, "mineable/axe"),
            new Block(322, "Spruce Button", "spruce_button", 0.5, 0.5, 6576, 6599, 6585, true, 0, "mineable/axe"),
            new Block(323, "Birch Button", "birch_button", 0.5, 0.5, 6600, 6623, 6609, true, 0, "mineable/axe"),
            new Block(324, "Jungle Button", "jungle_button", 0.5, 0.5, 6624, 6647, 6633, true, 0, "mineable/axe"),
            new Block(325, "Acacia Button", "acacia_button", 0.5, 0.5, 6648, 6671, 6657, true, 0, "mineable/axe"),
            new Block(326, "Dark Oak Button", "dark_oak_button", 0.5, 0.5, 6672, 6695, 6681, true, 0, "mineable/axe"),
            new Block(327, "Skeleton Skull", "skeleton_skull", 1, 1, 6696, 6711, 6696, true, 0, "default"),
            new Block(328, "Skeleton Wall Skull", "skeleton_wall_skull", 1, 1, 6712, 6715, 6712, true, 0, "default"),
            new Block(329, "Wither Skeleton Skull", "wither_skeleton_skull", 1, 1, 6716, 6731, 6716, true, 0, "default"),
            new Block(330, "Wither Skeleton Wall Skull", "wither_skeleton_wall_skull", 1, 1, 6732, 6735, 6732, true, 0, "default"),
            new Block(331, "Zombie Head", "zombie_head", 1, 1, 6736, 6751, 6736, true, 0, "default"),
            new Block(332, "Zombie Wall Head", "zombie_wall_head", 1, 1, 6752, 6755, 6752, true, 0, "default"),
            new Block(333, "Player Head", "player_head", 1, 1, 6756, 6771, 6756, true, 0, "default"),
            new Block(334, "Player Wall Head", "player_wall_head", 1, 1, 6772, 6775, 6772, true, 0, "default"),
            new Block(335, "Creeper Head", "creeper_head", 1, 1, 6776, 6791, 6776, true, 0, "default"),
            new Block(336, "Creeper Wall Head", "creeper_wall_head", 1, 1, 6792, 6795, 6792, true, 0, "default"),
            new Block(337, "Dragon Head", "dragon_head", 1, 1, 6796, 6811, 6796, true, 0, "default"),
            new Block(338, "Dragon Wall Head", "dragon_wall_head", 1, 1, 6812, 6815, 6812, true, 0, "default"),
            new Block(339, "Anvil", "anvil", 5, 1200, 6816, 6819, 6816, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(340, "Chipped Anvil", "chipped_anvil", 5, 1200, 6820, 6823, 6820, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(341, "Damaged Anvil", "damaged_anvil", 5, 1200, 6824, 6827, 6824, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(342, "Trapped Chest", "trapped_chest", 2.5, 2.5, 6828, 6851, 6829, true, 0, "mineable/axe"),
            new Block(343, "Light Weighted Pressure Plate", "light_weighted_pressure_plate", 0.5, 0.5, 6852, 6867, 6852, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(344, "Heavy Weighted Pressure Plate", "heavy_weighted_pressure_plate", 0.5, 0.5, 6868, 6883, 6868, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(345, "Redstone Comparator", "comparator", 0.0, 0.0, 6884, 6899, 6885, true, 0, "default"),
            new Block(346, "Daylight Detector", "daylight_detector", 0.2, 0.2, 6900, 6931, 6916, true, 0, "mineable/axe"),
            new Block(347, "Block of Redstone", "redstone_block", 5, 6, 6932, 6932, 6932, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(348, "Nether Quartz Ore", "nether_quartz_ore", 3, 3, 6933, 6933, 6933, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(349, "Hopper", "hopper", 3, 4.8, 6934, 6943, 6934, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(350, "Block of Quartz", "quartz_block", 0.8, 0.8, 6944, 6944, 6944, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(351, "Chiseled Quartz Block", "chiseled_quartz_block", 0.8, 0.8, 6945, 6945, 6945, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(352, "Quartz Pillar", "quartz_pillar", 0.8, 0.8, 6946, 6948, 6947, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(353, "Quartz Stairs", "quartz_stairs", 0.8, 0.8, 6949, 7028, 6960, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(354, "Activator Rail", "activator_rail", 0.7, 0.7, 7029, 7052, 7042, true, 0, "mineable/pickaxe"),
            new Block(355, "Dropper", "dropper", 3.5, 3.5, 7053, 7064, 7054, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(356, "White Terracotta", "white_terracotta", 1.25, 4.2, 7065, 7065, 7065, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(357, "Orange Terracotta", "orange_terracotta", 1.25, 4.2, 7066, 7066, 7066, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(358, "Magenta Terracotta", "magenta_terracotta", 1.25, 4.2, 7067, 7067, 7067, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(359, "Light Blue Terracotta", "light_blue_terracotta", 1.25, 4.2, 7068, 7068, 7068, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(360, "Yellow Terracotta", "yellow_terracotta", 1.25, 4.2, 7069, 7069, 7069, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(361, "Lime Terracotta", "lime_terracotta", 1.25, 4.2, 7070, 7070, 7070, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(362, "Pink Terracotta", "pink_terracotta", 1.25, 4.2, 7071, 7071, 7071, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(363, "Gray Terracotta", "gray_terracotta", 1.25, 4.2, 7072, 7072, 7072, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(364, "Light Gray Terracotta", "light_gray_terracotta", 1.25, 4.2, 7073, 7073, 7073, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(365, "Cyan Terracotta", "cyan_terracotta", 1.25, 4.2, 7074, 7074, 7074, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(366, "Purple Terracotta", "purple_terracotta", 1.25, 4.2, 7075, 7075, 7075, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(367, "Blue Terracotta", "blue_terracotta", 1.25, 4.2, 7076, 7076, 7076, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(368, "Brown Terracotta", "brown_terracotta", 1.25, 4.2, 7077, 7077, 7077, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(369, "Green Terracotta", "green_terracotta", 1.25, 4.2, 7078, 7078, 7078, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(370, "Red Terracotta", "red_terracotta", 1.25, 4.2, 7079, 7079, 7079, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(371, "Black Terracotta", "black_terracotta", 1.25, 4.2, 7080, 7080, 7080, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(372, "White Stained Glass Pane", "white_stained_glass_pane", 0.3, 0.3, 7081, 7112, 7112, true, 0, "default"),
            new Block(373, "Orange Stained Glass Pane", "orange_stained_glass_pane", 0.3, 0.3, 7113, 7144, 7144, true, 0, "default"),
            new Block(374, "Magenta Stained Glass Pane", "magenta_stained_glass_pane", 0.3, 0.3, 7145, 7176, 7176, true, 0, "default"),
            new Block(375, "Light Blue Stained Glass Pane", "light_blue_stained_glass_pane", 0.3, 0.3, 7177, 7208, 7208, true, 0, "default"),
            new Block(376, "Yellow Stained Glass Pane", "yellow_stained_glass_pane", 0.3, 0.3, 7209, 7240, 7240, true, 0, "default"),
            new Block(377, "Lime Stained Glass Pane", "lime_stained_glass_pane", 0.3, 0.3, 7241, 7272, 7272, true, 0, "default"),
            new Block(378, "Pink Stained Glass Pane", "pink_stained_glass_pane", 0.3, 0.3, 7273, 7304, 7304, true, 0, "default"),
            new Block(379, "Gray Stained Glass Pane", "gray_stained_glass_pane", 0.3, 0.3, 7305, 7336, 7336, true, 0, "default"),
            new Block(380, "Light Gray Stained Glass Pane", "light_gray_stained_glass_pane", 0.3, 0.3, 7337, 7368, 7368, true, 0, "default"),
            new Block(381, "Cyan Stained Glass Pane", "cyan_stained_glass_pane", 0.3, 0.3, 7369, 7400, 7400, true, 0, "default"),
            new Block(382, "Purple Stained Glass Pane", "purple_stained_glass_pane", 0.3, 0.3, 7401, 7432, 7432, true, 0, "default"),
            new Block(383, "Blue Stained Glass Pane", "blue_stained_glass_pane", 0.3, 0.3, 7433, 7464, 7464, true, 0, "default"),
            new Block(384, "Brown Stained Glass Pane", "brown_stained_glass_pane", 0.3, 0.3, 7465, 7496, 7496, true, 0, "default"),
            new Block(385, "Green Stained Glass Pane", "green_stained_glass_pane", 0.3, 0.3, 7497, 7528, 7528, true, 0, "default"),
            new Block(386, "Red Stained Glass Pane", "red_stained_glass_pane", 0.3, 0.3, 7529, 7560, 7560, true, 0, "default"),
            new Block(387, "Black Stained Glass Pane", "black_stained_glass_pane", 0.3, 0.3, 7561, 7592, 7592, true, 0, "default"),
            new Block(388, "Acacia Stairs", "acacia_stairs", 2, 3, 7593, 7672, 7604, true, 0, "mineable/axe"),
            new Block(389, "Dark Oak Stairs", "dark_oak_stairs", 2, 3, 7673, 7752, 7684, true, 0, "mineable/axe"),
            new Block(390, "Slime Block", "slime_block", 0.0, 0.0, 7753, 7753, 7753, true, 0, "default"),
            new Block(391, "Barrier", "barrier", 0.0, 3600000.75, 7754, 7754, 7754, false, 0, "default"),
            new Block(392, "Light", "light", 0.0, 3600000.75, 7755, 7786, 7786, false, 15, "default"),
            new Block(393, "Iron Trapdoor", "iron_trapdoor", 5, 5, 7787, 7850, 7802, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(394, "Prismarine", "prismarine", 1.5, 6, 7851, 7851, 7851, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(395, "Prismarine Bricks", "prismarine_bricks", 1.5, 6, 7852, 7852, 7852, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(396, "Dark Prismarine", "dark_prismarine", 1.5, 6, 7853, 7853, 7853, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(397, "Prismarine Stairs", "prismarine_stairs", 1.5, 6, 7854, 7933, 7865, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(398, "Prismarine Brick Stairs", "prismarine_brick_stairs", 1.5, 6, 7934, 8013, 7945, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(399, "Dark Prismarine Stairs", "dark_prismarine_stairs", 1.5, 6, 8014, 8093, 8025, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(400, "Prismarine Slab", "prismarine_slab", 1.5, 6, 8094, 8099, 8097, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(401, "Prismarine Brick Slab", "prismarine_brick_slab", 1.5, 6, 8100, 8105, 8103, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(402, "Dark Prismarine Slab", "dark_prismarine_slab", 1.5, 6, 8106, 8111, 8109, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(403, "Sea Lantern", "sea_lantern", 0.3, 0.3, 8112, 8112, 8112, true, 15, "default"),
            new Block(404, "Hay Bale", "hay_block", 0.5, 0.5, 8113, 8115, 8114, true, 0, "mineable/hoe"),
            new Block(405, "White Carpet", "white_carpet", 0.1, 0.1, 8116, 8116, 8116, true, 0, "default"),
            new Block(406, "Orange Carpet", "orange_carpet", 0.1, 0.1, 8117, 8117, 8117, true, 0, "default"),
            new Block(407, "Magenta Carpet", "magenta_carpet", 0.1, 0.1, 8118, 8118, 8118, true, 0, "default"),
            new Block(408, "Light Blue Carpet", "light_blue_carpet", 0.1, 0.1, 8119, 8119, 8119, true, 0, "default"),
            new Block(409, "Yellow Carpet", "yellow_carpet", 0.1, 0.1, 8120, 8120, 8120, true, 0, "default"),
            new Block(410, "Lime Carpet", "lime_carpet", 0.1, 0.1, 8121, 8121, 8121, true, 0, "default"),
            new Block(411, "Pink Carpet", "pink_carpet", 0.1, 0.1, 8122, 8122, 8122, true, 0, "default"),
            new Block(412, "Gray Carpet", "gray_carpet", 0.1, 0.1, 8123, 8123, 8123, true, 0, "default"),
            new Block(413, "Light Gray Carpet", "light_gray_carpet", 0.1, 0.1, 8124, 8124, 8124, true, 0, "default"),
            new Block(414, "Cyan Carpet", "cyan_carpet", 0.1, 0.1, 8125, 8125, 8125, true, 0, "default"),
            new Block(415, "Purple Carpet", "purple_carpet", 0.1, 0.1, 8126, 8126, 8126, true, 0, "default"),
            new Block(416, "Blue Carpet", "blue_carpet", 0.1, 0.1, 8127, 8127, 8127, true, 0, "default"),
            new Block(417, "Brown Carpet", "brown_carpet", 0.1, 0.1, 8128, 8128, 8128, true, 0, "default"),
            new Block(418, "Green Carpet", "green_carpet", 0.1, 0.1, 8129, 8129, 8129, true, 0, "default"),
            new Block(419, "Red Carpet", "red_carpet", 0.1, 0.1, 8130, 8130, 8130, true, 0, "default"),
            new Block(420, "Black Carpet", "black_carpet", 0.1, 0.1, 8131, 8131, 8131, true, 0, "default"),
            new Block(421, "Terracotta", "terracotta", 1.25, 4.2, 8132, 8132, 8132, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(422, "Block of Coal", "coal_block", 5, 6, 8133, 8133, 8133, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(423, "Packed Ice", "packed_ice", 0.5, 0.5, 8134, 8134, 8134, true, 0, "mineable/pickaxe"),
            new Block(424, "Sunflower", "sunflower", 0.0, 0.0, 8135, 8136, 8136, true, 0, "plant;mineable/axe"),
            new Block(425, "Lilac", "lilac", 0.0, 0.0, 8137, 8138, 8138, true, 0, "plant;mineable/axe"),
            new Block(426, "Rose Bush", "rose_bush", 0.0, 0.0, 8139, 8140, 8140, true, 0, "plant;mineable/axe"),
            new Block(427, "Peony", "peony", 0.0, 0.0, 8141, 8142, 8142, true, 0, "plant;mineable/axe"),
            new Block(428, "Tall Grass", "tall_grass", 0.0, 0.0, 8143, 8144, 8144, true, 0, "plant;mineable/axe"),
            new Block(429, "Large Fern", "large_fern", 0.0, 0.0, 8145, 8146, 8146, true, 0, "plant;mineable/axe"),
            new Block(430, "White Banner", "white_banner", 1, 1, 8147, 8162, 8147, true, 0, "mineable/axe"),
            new Block(431, "Orange Banner", "orange_banner", 1, 1, 8163, 8178, 8163, true, 0, "mineable/axe"),
            new Block(432, "Magenta Banner", "magenta_banner", 1, 1, 8179, 8194, 8179, true, 0, "mineable/axe"),
            new Block(433, "Light Blue Banner", "light_blue_banner", 1, 1, 8195, 8210, 8195, true, 0, "mineable/axe"),
            new Block(434, "Yellow Banner", "yellow_banner", 1, 1, 8211, 8226, 8211, true, 0, "mineable/axe"),
            new Block(435, "Lime Banner", "lime_banner", 1, 1, 8227, 8242, 8227, true, 0, "mineable/axe"),
            new Block(436, "Pink Banner", "pink_banner", 1, 1, 8243, 8258, 8243, true, 0, "mineable/axe"),
            new Block(437, "Gray Banner", "gray_banner", 1, 1, 8259, 8274, 8259, true, 0, "mineable/axe"),
            new Block(438, "Light Gray Banner", "light_gray_banner", 1, 1, 8275, 8290, 8275, true, 0, "mineable/axe"),
            new Block(439, "Cyan Banner", "cyan_banner", 1, 1, 8291, 8306, 8291, true, 0, "mineable/axe"),
            new Block(440, "Purple Banner", "purple_banner", 1, 1, 8307, 8322, 8307, true, 0, "mineable/axe"),
            new Block(441, "Blue Banner", "blue_banner", 1, 1, 8323, 8338, 8323, true, 0, "mineable/axe"),
            new Block(442, "Brown Banner", "brown_banner", 1, 1, 8339, 8354, 8339, true, 0, "mineable/axe"),
            new Block(443, "Green Banner", "green_banner", 1, 1, 8355, 8370, 8355, true, 0, "mineable/axe"),
            new Block(444, "Red Banner", "red_banner", 1, 1, 8371, 8386, 8371, true, 0, "mineable/axe"),
            new Block(445, "Black Banner", "black_banner", 1, 1, 8387, 8402, 8387, true, 0, "mineable/axe"),
            new Block(446, "White wall banner", "white_wall_banner", 1, 1, 8403, 8406, 8403, true, 0, "mineable/axe"),
            new Block(447, "Orange wall banner", "orange_wall_banner", 1, 1, 8407, 8410, 8407, true, 0, "mineable/axe"),
            new Block(448, "Magenta wall banner", "magenta_wall_banner", 1, 1, 8411, 8414, 8411, true, 0, "mineable/axe"),
            new Block(449, "Light blue wall banner", "light_blue_wall_banner", 1, 1, 8415, 8418, 8415, true, 0, "mineable/axe"),
            new Block(450, "Yellow wall banner", "yellow_wall_banner", 1, 1, 8419, 8422, 8419, true, 0, "mineable/axe"),
            new Block(451, "Lime wall banner", "lime_wall_banner", 1, 1, 8423, 8426, 8423, true, 0, "mineable/axe"),
            new Block(452, "Pink wall banner", "pink_wall_banner", 1, 1, 8427, 8430, 8427, true, 0, "mineable/axe"),
            new Block(453, "Gray wall banner", "gray_wall_banner", 1, 1, 8431, 8434, 8431, true, 0, "mineable/axe"),
            new Block(454, "Light gray wall banner", "light_gray_wall_banner", 1, 1, 8435, 8438, 8435, true, 0, "mineable/axe"),
            new Block(455, "Cyan wall banner", "cyan_wall_banner", 1, 1, 8439, 8442, 8439, true, 0, "mineable/axe"),
            new Block(456, "Purple wall banner", "purple_wall_banner", 1, 1, 8443, 8446, 8443, true, 0, "mineable/axe"),
            new Block(457, "Blue wall banner", "blue_wall_banner", 1, 1, 8447, 8450, 8447, true, 0, "mineable/axe"),
            new Block(458, "Brown wall banner", "brown_wall_banner", 1, 1, 8451, 8454, 8451, true, 0, "mineable/axe"),
            new Block(459, "Green wall banner", "green_wall_banner", 1, 1, 8455, 8458, 8455, true, 0, "mineable/axe"),
            new Block(460, "Red wall banner", "red_wall_banner", 1, 1, 8459, 8462, 8459, true, 0, "mineable/axe"),
            new Block(461, "Black wall banner", "black_wall_banner", 1, 1, 8463, 8466, 8463, true, 0, "mineable/axe"),
            new Block(462, "Red Sandstone", "red_sandstone", 0.8, 0.8, 8467, 8467, 8467, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(463, "Chiseled Red Sandstone", "chiseled_red_sandstone", 0.8, 0.8, 8468, 8468, 8468, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(464, "Cut Red Sandstone", "cut_red_sandstone", 0.8, 0.8, 8469, 8469, 8469, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(465, "Red Sandstone Stairs", "red_sandstone_stairs", 0.8, 0.8, 8470, 8549, 8481, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(466, "Oak Slab", "oak_slab", 2, 3, 8550, 8555, 8553, true, 0, "mineable/axe"),
            new Block(467, "Spruce Slab", "spruce_slab", 2, 3, 8556, 8561, 8559, true, 0, "mineable/axe"),
            new Block(468, "Birch Slab", "birch_slab", 2, 3, 8562, 8567, 8565, true, 0, "mineable/axe"),
            new Block(469, "Jungle Slab", "jungle_slab", 2, 3, 8568, 8573, 8571, true, 0, "mineable/axe"),
            new Block(470, "Acacia Slab", "acacia_slab", 2, 3, 8574, 8579, 8577, true, 0, "mineable/axe"),
            new Block(471, "Dark Oak Slab", "dark_oak_slab", 2, 3, 8580, 8585, 8583, true, 0, "mineable/axe"),
            new Block(472, "Stone Slab", "stone_slab", 2, 6, 8586, 8591, 8589, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(473, "Smooth Stone Slab", "smooth_stone_slab", 2, 6, 8592, 8597, 8595, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(474, "Sandstone Slab", "sandstone_slab", 2, 6, 8598, 8603, 8601, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(475, "Cut Sandstone Slab", "cut_sandstone_slab", 2, 6, 8604, 8609, 8607, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(476, "Petrified Oak Slab", "petrified_oak_slab", 2, 6, 8610, 8615, 8613, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(477, "Cobblestone Slab", "cobblestone_slab", 2, 6, 8616, 8621, 8619, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(478, "Brick Slab", "brick_slab", 2, 6, 8622, 8627, 8625, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(479, "Stone Brick Slab", "stone_brick_slab", 2, 6, 8628, 8633, 8631, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(480, "Nether Brick Slab", "nether_brick_slab", 2, 6, 8634, 8639, 8637, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(481, "Quartz Slab", "quartz_slab", 2, 6, 8640, 8645, 8643, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(482, "Red Sandstone Slab", "red_sandstone_slab", 2, 6, 8646, 8651, 8649, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(483, "Cut Red Sandstone Slab", "cut_red_sandstone_slab", 2, 6, 8652, 8657, 8655, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(484, "Purpur Slab", "purpur_slab", 2, 6, 8658, 8663, 8661, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(485, "Smooth Stone", "smooth_stone", 2, 6, 8664, 8664, 8664, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(486, "Smooth Sandstone", "smooth_sandstone", 2, 6, 8665, 8665, 8665, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(487, "Smooth Quartz Block", "smooth_quartz", 2, 6, 8666, 8666, 8666, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(488, "Smooth Red Sandstone", "smooth_red_sandstone", 2, 6, 8667, 8667, 8667, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(489, "Spruce Fence Gate", "spruce_fence_gate", 2, 3, 8668, 8699, 8675, true, 0, "mineable/axe"),
            new Block(490, "Birch Fence Gate", "birch_fence_gate", 2, 3, 8700, 8731, 8707, true, 0, "mineable/axe"),
            new Block(491, "Jungle Fence Gate", "jungle_fence_gate", 2, 3, 8732, 8763, 8739, true, 0, "mineable/axe"),
            new Block(492, "Acacia Fence Gate", "acacia_fence_gate", 2, 3, 8764, 8795, 8771, true, 0, "mineable/axe"),
            new Block(493, "Dark Oak Fence Gate", "dark_oak_fence_gate", 2, 3, 8796, 8827, 8803, true, 0, "mineable/axe"),
            new Block(494, "Spruce Fence", "spruce_fence", 2, 3, 8828, 8859, 8859, true, 0, "mineable/axe"),
            new Block(495, "Birch Fence", "birch_fence", 2, 3, 8860, 8891, 8891, true, 0, "mineable/axe"),
            new Block(496, "Jungle Fence", "jungle_fence", 2, 3, 8892, 8923, 8923, true, 0, "mineable/axe"),
            new Block(497, "Acacia Fence", "acacia_fence", 2, 3, 8924, 8955, 8955, true, 0, "mineable/axe"),
            new Block(498, "Dark Oak Fence", "dark_oak_fence", 2, 3, 8956, 8987, 8987, true, 0, "mineable/axe"),
            new Block(499, "Spruce Door", "spruce_door", 3, 3, 8988, 9051, 8999, true, 0, "mineable/axe"),
            new Block(500, "Birch Door", "birch_door", 3, 3, 9052, 9115, 9063, true, 0, "mineable/axe"),
            new Block(501, "Jungle Door", "jungle_door", 3, 3, 9116, 9179, 9127, true, 0, "mineable/axe"),
            new Block(502, "Acacia Door", "acacia_door", 3, 3, 9180, 9243, 9191, true, 0, "mineable/axe"),
            new Block(503, "Dark Oak Door", "dark_oak_door", 3, 3, 9244, 9307, 9255, true, 0, "mineable/axe"),
            new Block(504, "End Rod", "end_rod", 0.0, 0.0, 9308, 9313, 9312, true, 14, "default"),
            new Block(505, "Chorus Plant", "chorus_plant", 0.4, 0.4, 9314, 9377, 9377, true, 0, "plant;mineable/axe"),
            new Block(506, "Chorus Flower", "chorus_flower", 0.4, 0.4, 9378, 9383, 9378, true, 0, "plant;mineable/axe"),
            new Block(507, "Purpur Block", "purpur_block", 1.5, 6, 9384, 9384, 9384, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(508, "Purpur Pillar", "purpur_pillar", 1.5, 6, 9385, 9387, 9386, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(509, "Purpur Stairs", "purpur_stairs", 1.5, 6, 9388, 9467, 9399, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(510, "End Stone Bricks", "end_stone_bricks", 3, 9, 9468, 9468, 9468, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(511, "Beetroots", "beetroots", 0.0, 0.0, 9469, 9472, 9469, true, 0, "plant;mineable/axe"),
            new Block(512, "Dirt Path", "dirt_path", 0.65, 0.65, 9473, 9473, 9473, true, 0, "mineable/shovel"),
            new Block(513, "End Gateway", "end_gateway", 0.0, 3600000, 9474, 9474, 9474, false, 15, "default"),
            new Block(514, "Repeating Command Block", "repeating_command_block", 0.0, 3600000, 9475, 9486, 9481, false, 0, "default", new int[]{}),
            new Block(515, "Chain Command Block", "chain_command_block", 0.0, 3600000, 9487, 9498, 9493, false, 0, "default", new int[]{}),
            new Block(516, "Frosted Ice", "frosted_ice", 0.5, 0.5, 9499, 9502, 9499, true, 0, "default"),
            new Block(517, "Magma Block", "magma_block", 0.5, 0.5, 9503, 9503, 9503, true, 3, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(518, "Nether Wart Block", "nether_wart_block", 1, 1, 9504, 9504, 9504, true, 0, "mineable/hoe"),
            new Block(519, "Red Nether Bricks", "red_nether_bricks", 2, 6, 9505, 9505, 9505, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(520, "Bone Block", "bone_block", 2, 2, 9506, 9508, 9507, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(521, "Structure Void", "structure_void", 0.0, 0.0, 9509, 9509, 9509, true, 0, "default"),
            new Block(522, "Observer", "observer", 3, 3, 9510, 9521, 9515, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(523, "Shulker Box", "shulker_box", 2, 2, 9522, 9527, 9526, true, 0, "mineable/pickaxe"),
            new Block(524, "White Shulker Box", "white_shulker_box", 2, 2, 9528, 9533, 9532, true, 0, "mineable/pickaxe"),
            new Block(525, "Orange Shulker Box", "orange_shulker_box", 2, 2, 9534, 9539, 9538, true, 0, "mineable/pickaxe"),
            new Block(526, "Magenta Shulker Box", "magenta_shulker_box", 2, 2, 9540, 9545, 9544, true, 0, "mineable/pickaxe"),
            new Block(527, "Light Blue Shulker Box", "light_blue_shulker_box", 2, 2, 9546, 9551, 9550, true, 0, "mineable/pickaxe"),
            new Block(528, "Yellow Shulker Box", "yellow_shulker_box", 2, 2, 9552, 9557, 9556, true, 0, "mineable/pickaxe"),
            new Block(529, "Lime Shulker Box", "lime_shulker_box", 2, 2, 9558, 9563, 9562, true, 0, "mineable/pickaxe"),
            new Block(530, "Pink Shulker Box", "pink_shulker_box", 2, 2, 9564, 9569, 9568, true, 0, "mineable/pickaxe"),
            new Block(531, "Gray Shulker Box", "gray_shulker_box", 2, 2, 9570, 9575, 9574, true, 0, "mineable/pickaxe"),
            new Block(532, "Light Gray Shulker Box", "light_gray_shulker_box", 2, 2, 9576, 9581, 9580, true, 0, "mineable/pickaxe"),
            new Block(533, "Cyan Shulker Box", "cyan_shulker_box", 2, 2, 9582, 9587, 9586, true, 0, "mineable/pickaxe"),
            new Block(534, "Purple Shulker Box", "purple_shulker_box", 2, 2, 9588, 9593, 9592, true, 0, "mineable/pickaxe"),
            new Block(535, "Blue Shulker Box", "blue_shulker_box", 2, 2, 9594, 9599, 9598, true, 0, "mineable/pickaxe"),
            new Block(536, "Brown Shulker Box", "brown_shulker_box", 2, 2, 9600, 9605, 9604, true, 0, "mineable/pickaxe"),
            new Block(537, "Green Shulker Box", "green_shulker_box", 2, 2, 9606, 9611, 9610, true, 0, "mineable/pickaxe"),
            new Block(538, "Red Shulker Box", "red_shulker_box", 2, 2, 9612, 9617, 9616, true, 0, "mineable/pickaxe"),
            new Block(539, "Black Shulker Box", "black_shulker_box", 2, 2, 9618, 9623, 9622, true, 0, "mineable/pickaxe"),
            new Block(540, "White Glazed Terracotta", "white_glazed_terracotta", 1.4, 1.4, 9624, 9627, 9624, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(541, "Orange Glazed Terracotta", "orange_glazed_terracotta", 1.4, 1.4, 9628, 9631, 9628, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(542, "Magenta Glazed Terracotta", "magenta_glazed_terracotta", 1.4, 1.4, 9632, 9635, 9632, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(543, "Light Blue Glazed Terracotta", "light_blue_glazed_terracotta", 1.4, 1.4, 9636, 9639, 9636, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(544, "Yellow Glazed Terracotta", "yellow_glazed_terracotta", 1.4, 1.4, 9640, 9643, 9640, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(545, "Lime Glazed Terracotta", "lime_glazed_terracotta", 1.4, 1.4, 9644, 9647, 9644, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(546, "Pink Glazed Terracotta", "pink_glazed_terracotta", 1.4, 1.4, 9648, 9651, 9648, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(547, "Gray Glazed Terracotta", "gray_glazed_terracotta", 1.4, 1.4, 9652, 9655, 9652, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(548, "Light Gray Glazed Terracotta", "light_gray_glazed_terracotta", 1.4, 1.4, 9656, 9659, 9656, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(549, "Cyan Glazed Terracotta", "cyan_glazed_terracotta", 1.4, 1.4, 9660, 9663, 9660, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(550, "Purple Glazed Terracotta", "purple_glazed_terracotta", 1.4, 1.4, 9664, 9667, 9664, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(551, "Blue Glazed Terracotta", "blue_glazed_terracotta", 1.4, 1.4, 9668, 9671, 9668, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(552, "Brown Glazed Terracotta", "brown_glazed_terracotta", 1.4, 1.4, 9672, 9675, 9672, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(553, "Green Glazed Terracotta", "green_glazed_terracotta", 1.4, 1.4, 9676, 9679, 9676, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(554, "Red Glazed Terracotta", "red_glazed_terracotta", 1.4, 1.4, 9680, 9683, 9680, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(555, "Black Glazed Terracotta", "black_glazed_terracotta", 1.4, 1.4, 9684, 9687, 9684, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(556, "White Concrete", "white_concrete", 1.8, 1.8, 9688, 9688, 9688, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(557, "Orange Concrete", "orange_concrete", 1.8, 1.8, 9689, 9689, 9689, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(558, "Magenta Concrete", "magenta_concrete", 1.8, 1.8, 9690, 9690, 9690, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(559, "Light Blue Concrete", "light_blue_concrete", 1.8, 1.8, 9691, 9691, 9691, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(560, "Yellow Concrete", "yellow_concrete", 1.8, 1.8, 9692, 9692, 9692, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(561, "Lime Concrete", "lime_concrete", 1.8, 1.8, 9693, 9693, 9693, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(562, "Pink Concrete", "pink_concrete", 1.8, 1.8, 9694, 9694, 9694, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(563, "Gray Concrete", "gray_concrete", 1.8, 1.8, 9695, 9695, 9695, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(564, "Light Gray Concrete", "light_gray_concrete", 1.8, 1.8, 9696, 9696, 9696, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(565, "Cyan Concrete", "cyan_concrete", 1.8, 1.8, 9697, 9697, 9697, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(566, "Purple Concrete", "purple_concrete", 1.8, 1.8, 9698, 9698, 9698, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(567, "Blue Concrete", "blue_concrete", 1.8, 1.8, 9699, 9699, 9699, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(568, "Brown Concrete", "brown_concrete", 1.8, 1.8, 9700, 9700, 9700, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(569, "Green Concrete", "green_concrete", 1.8, 1.8, 9701, 9701, 9701, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(570, "Red Concrete", "red_concrete", 1.8, 1.8, 9702, 9702, 9702, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(571, "Black Concrete", "black_concrete", 1.8, 1.8, 9703, 9703, 9703, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(572, "White Concrete Powder", "white_concrete_powder", 0.5, 0.5, 9704, 9704, 9704, true, 0, "mineable/shovel"),
            new Block(573, "Orange Concrete Powder", "orange_concrete_powder", 0.5, 0.5, 9705, 9705, 9705, true, 0, "mineable/shovel"),
            new Block(574, "Magenta Concrete Powder", "magenta_concrete_powder", 0.5, 0.5, 9706, 9706, 9706, true, 0, "mineable/shovel"),
            new Block(575, "Light Blue Concrete Powder", "light_blue_concrete_powder", 0.5, 0.5, 9707, 9707, 9707, true, 0, "mineable/shovel"),
            new Block(576, "Yellow Concrete Powder", "yellow_concrete_powder", 0.5, 0.5, 9708, 9708, 9708, true, 0, "mineable/shovel"),
            new Block(577, "Lime Concrete Powder", "lime_concrete_powder", 0.5, 0.5, 9709, 9709, 9709, true, 0, "mineable/shovel"),
            new Block(578, "Pink Concrete Powder", "pink_concrete_powder", 0.5, 0.5, 9710, 9710, 9710, true, 0, "mineable/shovel"),
            new Block(579, "Gray Concrete Powder", "gray_concrete_powder", 0.5, 0.5, 9711, 9711, 9711, true, 0, "mineable/shovel"),
            new Block(580, "Light Gray Concrete Powder", "light_gray_concrete_powder", 0.5, 0.5, 9712, 9712, 9712, true, 0, "mineable/shovel"),
            new Block(581, "Cyan Concrete Powder", "cyan_concrete_powder", 0.5, 0.5, 9713, 9713, 9713, true, 0, "mineable/shovel"),
            new Block(582, "Purple Concrete Powder", "purple_concrete_powder", 0.5, 0.5, 9714, 9714, 9714, true, 0, "mineable/shovel"),
            new Block(583, "Blue Concrete Powder", "blue_concrete_powder", 0.5, 0.5, 9715, 9715, 9715, true, 0, "mineable/shovel"),
            new Block(584, "Brown Concrete Powder", "brown_concrete_powder", 0.5, 0.5, 9716, 9716, 9716, true, 0, "mineable/shovel"),
            new Block(585, "Green Concrete Powder", "green_concrete_powder", 0.5, 0.5, 9717, 9717, 9717, true, 0, "mineable/shovel"),
            new Block(586, "Red Concrete Powder", "red_concrete_powder", 0.5, 0.5, 9718, 9718, 9718, true, 0, "mineable/shovel"),
            new Block(587, "Black Concrete Powder", "black_concrete_powder", 0.5, 0.5, 9719, 9719, 9719, true, 0, "mineable/shovel"),
            new Block(588, "Kelp", "kelp", 0.0, 0.0, 9720, 9745, 9720, true, 0, "default"),
            new Block(589, "Kelp Plant", "kelp_plant", 0.0, 0.0, 9746, 9746, 9746, true, 0, "default"),
            new Block(590, "Dried Kelp Block", "dried_kelp_block", 0.5, 2.5, 9747, 9747, 9747, true, 0, "mineable/hoe"),
            new Block(591, "Turtle Egg", "turtle_egg", 0.5, 0.5, 9748, 9759, 9748, true, 0, "default"),
            new Block(592, "Dead Tube Coral Block", "dead_tube_coral_block", 1.5, 6, 9760, 9760, 9760, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(593, "Dead Brain Coral Block", "dead_brain_coral_block", 1.5, 6, 9761, 9761, 9761, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(594, "Dead Bubble Coral Block", "dead_bubble_coral_block", 1.5, 6, 9762, 9762, 9762, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(595, "Dead Fire Coral Block", "dead_fire_coral_block", 1.5, 6, 9763, 9763, 9763, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(596, "Dead Horn Coral Block", "dead_horn_coral_block", 1.5, 6, 9764, 9764, 9764, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(597, "Tube Coral Block", "tube_coral_block", 1.5, 6, 9765, 9765, 9765, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(598, "Brain Coral Block", "brain_coral_block", 1.5, 6, 9766, 9766, 9766, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(599, "Bubble Coral Block", "bubble_coral_block", 1.5, 6, 9767, 9767, 9767, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(600, "Fire Coral Block", "fire_coral_block", 1.5, 6, 9768, 9768, 9768, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(601, "Horn Coral Block", "horn_coral_block", 1.5, 6, 9769, 9769, 9769, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(602, "Dead Tube Coral", "dead_tube_coral", 0.0, 0.0, 9770, 9771, 9770, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(603, "Dead Brain Coral", "dead_brain_coral", 0.0, 0.0, 9772, 9773, 9772, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(604, "Dead Bubble Coral", "dead_bubble_coral", 0.0, 0.0, 9774, 9775, 9774, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(605, "Dead Fire Coral", "dead_fire_coral", 0.0, 0.0, 9776, 9777, 9776, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(606, "Dead Horn Coral", "dead_horn_coral", 0.0, 0.0, 9778, 9779, 9778, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(607, "Tube Coral", "tube_coral", 0.0, 0.0, 9780, 9781, 9780, true, 0, "default"),
            new Block(608, "Brain Coral", "brain_coral", 0.0, 0.0, 9782, 9783, 9782, true, 0, "default"),
            new Block(609, "Bubble Coral", "bubble_coral", 0.0, 0.0, 9784, 9785, 9784, true, 0, "default"),
            new Block(610, "Fire Coral", "fire_coral", 0.0, 0.0, 9786, 9787, 9786, true, 0, "default"),
            new Block(611, "Horn Coral", "horn_coral", 0.0, 0.0, 9788, 9789, 9788, true, 0, "default"),
            new Block(612, "Dead Tube Coral Fan", "dead_tube_coral_fan", 0.0, 0.0, 9790, 9791, 9790, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(613, "Dead Brain Coral Fan", "dead_brain_coral_fan", 0.0, 0.0, 9792, 9793, 9792, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(614, "Dead Bubble Coral Fan", "dead_bubble_coral_fan", 0.0, 0.0, 9794, 9795, 9794, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(615, "Dead Fire Coral Fan", "dead_fire_coral_fan", 0.0, 0.0, 9796, 9797, 9796, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(616, "Dead Horn Coral Fan", "dead_horn_coral_fan", 0.0, 0.0, 9798, 9799, 9798, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(617, "Tube Coral Fan", "tube_coral_fan", 0.0, 0.0, 9800, 9801, 9800, true, 0, "default"),
            new Block(618, "Brain Coral Fan", "brain_coral_fan", 0.0, 0.0, 9802, 9803, 9802, true, 0, "default"),
            new Block(619, "Bubble Coral Fan", "bubble_coral_fan", 0.0, 0.0, 9804, 9805, 9804, true, 0, "default"),
            new Block(620, "Fire Coral Fan", "fire_coral_fan", 0.0, 0.0, 9806, 9807, 9806, true, 0, "default"),
            new Block(621, "Horn Coral Fan", "horn_coral_fan", 0.0, 0.0, 9808, 9809, 9808, true, 0, "default"),
            new Block(622, "Dead Tube Coral Wall Fan", "dead_tube_coral_wall_fan", 0.0, 0.0, 9810, 9817, 9810, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(623, "Dead Brain Coral Wall Fan", "dead_brain_coral_wall_fan", 0.0, 0.0, 9818, 9825, 9818, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(624, "Dead Bubble Coral Wall Fan", "dead_bubble_coral_wall_fan", 0.0, 0.0, 9826, 9833, 9826, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(625, "Dead Fire Coral Wall Fan", "dead_fire_coral_wall_fan", 0.0, 0.0, 9834, 9841, 9834, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(626, "Dead Horn Coral Wall Fan", "dead_horn_coral_wall_fan", 0.0, 0.0, 9842, 9849, 9842, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(627, "Tube Coral Wall Fan", "tube_coral_wall_fan", 0.0, 0.0, 9850, 9857, 9850, true, 0, "default"),
            new Block(628, "Brain Coral Wall Fan", "brain_coral_wall_fan", 0.0, 0.0, 9858, 9865, 9858, true, 0, "default"),
            new Block(629, "Bubble Coral Wall Fan", "bubble_coral_wall_fan", 0.0, 0.0, 9866, 9873, 9866, true, 0, "default"),
            new Block(630, "Fire Coral Wall Fan", "fire_coral_wall_fan", 0.0, 0.0, 9874, 9881, 9874, true, 0, "default"),
            new Block(631, "Horn Coral Wall Fan", "horn_coral_wall_fan", 0.0, 0.0, 9882, 9889, 9882, true, 0, "default"),
            new Block(632, "Sea Pickle", "sea_pickle", 0.0, 0.0, 9890, 9897, 9890, true, 6, "default"),
            new Block(633, "Blue Ice", "blue_ice", 2.8, 2.8, 9898, 9898, 9898, true, 0, "mineable/pickaxe"),
            new Block(634, "Conduit", "conduit", 3, 3, 9899, 9900, 9899, true, 15, "default"),
            new Block(635, "Bamboo Shoot", "bamboo_sapling", 1, 1, 9901, 9901, 9901, true, 0, "default"),
            new Block(636, "Bamboo", "bamboo", 1, 1, 9902, 9913, 9902, true, 0, "mineable/axe"),
            new Block(637, "Potted Bamboo", "potted_bamboo", 0.0, 0.0, 9914, 9914, 9914, true, 0, "default"),
            new Block(638, "Void Air", "void_air", 0.0, 0.0, 9915, 9915, 9915, true, 0, "default"),
            new Block(639, "Cave Air", "cave_air", 0.0, 0.0, 9916, 9916, 9916, true, 0, "default"),
            new Block(640, "Bubble Column", "bubble_column", 0.0, 0.0, 9917, 9918, 9917, true, 0, "default"),
            new Block(641, "Polished Granite Stairs", "polished_granite_stairs", 1.5, 6, 9919, 9998, 9930, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(642, "Smooth Red Sandstone Stairs", "smooth_red_sandstone_stairs", 2, 6, 9999, 10078, 10010, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(643, "Mossy Stone Brick Stairs", "mossy_stone_brick_stairs", 1.5, 6, 10079, 10158, 10090, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(644, "Polished Diorite Stairs", "polished_diorite_stairs", 1.5, 6, 10159, 10238, 10170, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(645, "Mossy Cobblestone Stairs", "mossy_cobblestone_stairs", 2, 6, 10239, 10318, 10250, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(646, "End Stone Brick Stairs", "end_stone_brick_stairs", 3, 9, 10319, 10398, 10330, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(647, "Stone Stairs", "stone_stairs", 1.5, 6, 10399, 10478, 10410, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(648, "Smooth Sandstone Stairs", "smooth_sandstone_stairs", 2, 6, 10479, 10558, 10490, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(649, "Smooth Quartz Stairs", "smooth_quartz_stairs", 2, 6, 10559, 10638, 10570, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(650, "Granite Stairs", "granite_stairs", 1.5, 6, 10639, 10718, 10650, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(651, "Andesite Stairs", "andesite_stairs", 1.5, 6, 10719, 10798, 10730, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(652, "Red Nether Brick Stairs", "red_nether_brick_stairs", 2, 6, 10799, 10878, 10810, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(653, "Polished Andesite Stairs", "polished_andesite_stairs", 1.5, 6, 10879, 10958, 10890, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(654, "Diorite Stairs", "diorite_stairs", 1.5, 6, 10959, 11038, 10970, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(655, "Polished Granite Slab", "polished_granite_slab", 1.5, 6, 11039, 11044, 11042, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(656, "Smooth Red Sandstone Slab", "smooth_red_sandstone_slab", 2, 6, 11045, 11050, 11048, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(657, "Mossy Stone Brick Slab", "mossy_stone_brick_slab", 1.5, 6, 11051, 11056, 11054, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(658, "Polished Diorite Slab", "polished_diorite_slab", 1.5, 6, 11057, 11062, 11060, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(659, "Mossy Cobblestone Slab", "mossy_cobblestone_slab", 2, 6, 11063, 11068, 11066, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(660, "End Stone Brick Slab", "end_stone_brick_slab", 3, 9, 11069, 11074, 11072, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(661, "Smooth Sandstone Slab", "smooth_sandstone_slab", 2, 6, 11075, 11080, 11078, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(662, "Smooth Quartz Slab", "smooth_quartz_slab", 2, 6, 11081, 11086, 11084, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(663, "Granite Slab", "granite_slab", 1.5, 6, 11087, 11092, 11090, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(664, "Andesite Slab", "andesite_slab", 1.5, 6, 11093, 11098, 11096, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(665, "Red Nether Brick Slab", "red_nether_brick_slab", 2, 6, 11099, 11104, 11102, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(666, "Polished Andesite Slab", "polished_andesite_slab", 1.5, 6, 11105, 11110, 11108, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(667, "Diorite Slab", "diorite_slab", 1.5, 6, 11111, 11116, 11114, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(668, "Brick Wall", "brick_wall", 2, 6, 11117, 11440, 11120, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(669, "Prismarine Wall", "prismarine_wall", 1.5, 6, 11441, 11764, 11444, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(670, "Red Sandstone Wall", "red_sandstone_wall", 0.8, 0.8, 11765, 12088, 11768, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(671, "Mossy Stone Brick Wall", "mossy_stone_brick_wall", 1.5, 6, 12089, 12412, 12092, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(672, "Granite Wall", "granite_wall", 1.5, 6, 12413, 12736, 12416, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(673, "Stone Brick Wall", "stone_brick_wall", 1.5, 6, 12737, 13060, 12740, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(674, "Nether Brick Wall", "nether_brick_wall", 2, 6, 13061, 13384, 13064, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(675, "Andesite Wall", "andesite_wall", 1.5, 6, 13385, 13708, 13388, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(676, "Red Nether Brick Wall", "red_nether_brick_wall", 2, 6, 13709, 14032, 13712, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(677, "Sandstone Wall", "sandstone_wall", 0.8, 0.8, 14033, 14356, 14036, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(678, "End Stone Brick Wall", "end_stone_brick_wall", 3, 9, 14357, 14680, 14360, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(679, "Diorite Wall", "diorite_wall", 1.5, 6, 14681, 15004, 14684, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(680, "Scaffolding", "scaffolding", 0.0, 0.0, 15005, 15036, 15036, true, 0, "mineable/axe"),
            new Block(681, "Loom", "loom", 2.5, 2.5, 15037, 15040, 15037, true, 0, "mineable/axe"),
            new Block(682, "Barrel", "barrel", 2.5, 2.5, 15041, 15052, 15042, true, 0, "mineable/axe"),
            new Block(683, "Smoker", "smoker", 3.5, 3.5, 15053, 15060, 15054, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(684, "Blast Furnace", "blast_furnace", 3.5, 3.5, 15061, 15068, 15062, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(685, "Cartography Table", "cartography_table", 2.5, 2.5, 15069, 15069, 15069, true, 0, "mineable/axe"),
            new Block(686, "Fletching Table", "fletching_table", 2.5, 2.5, 15070, 15070, 15070, true, 0, "mineable/axe"),
            new Block(687, "Grindstone", "grindstone", 2, 6, 15071, 15082, 15075, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(688, "Lectern", "lectern", 2.5, 2.5, 15083, 15098, 15086, true, 0, "mineable/axe"),
            new Block(689, "Smithing Table", "smithing_table", 2.5, 2.5, 15099, 15099, 15099, true, 0, "mineable/axe"),
            new Block(690, "Stonecutter", "stonecutter", 3.5, 3.5, 15100, 15103, 15100, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(691, "Bell", "bell", 5, 5, 15104, 15135, 15105, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(692, "Lantern", "lantern", 3.5, 3.5, 15136, 15139, 15139, true, 15, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(693, "Soul Lantern", "soul_lantern", 3.5, 3.5, 15140, 15143, 15143, true, 10, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(694, "Campfire", "campfire", 2, 2, 15144, 15175, 15147, true, 15, "mineable/axe"),
            new Block(695, "Soul Campfire", "soul_campfire", 2, 2, 15176, 15207, 15179, true, 10, "mineable/axe"),
            new Block(696, "Sweet Berry Bush", "sweet_berry_bush", 0.0, 0.0, 15208, 15211, 15208, true, 0, "plant;mineable/axe"),
            new Block(697, "Warped Stem", "warped_stem", 2, 2, 15212, 15214, 15213, true, 0, "mineable/axe"),
            new Block(698, "Stripped Warped Stem", "stripped_warped_stem", 2, 2, 15215, 15217, 15216, true, 0, "mineable/axe"),
            new Block(699, "Warped Hyphae", "warped_hyphae", 2, 2, 15218, 15220, 15219, true, 0, "mineable/axe"),
            new Block(700, "Stripped Warped Hyphae", "stripped_warped_hyphae", 2, 2, 15221, 15223, 15222, true, 0, "mineable/axe"),
            new Block(701, "Warped Nylium", "warped_nylium", 0.4, 0.4, 15224, 15224, 15224, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(702, "Warped Fungus", "warped_fungus", 0.0, 0.0, 15225, 15225, 15225, true, 0, "plant;mineable/axe"),
            new Block(703, "Warped Wart Block", "warped_wart_block", 1, 1, 15226, 15226, 15226, true, 0, "mineable/hoe"),
            new Block(704, "Warped Roots", "warped_roots", 0.0, 0.0, 15227, 15227, 15227, true, 0, "default"),
            new Block(705, "Nether Sprouts", "nether_sprouts", 0.0, 0.0, 15228, 15228, 15228, true, 0, "default"),
            new Block(706, "Crimson Stem", "crimson_stem", 2, 2, 15229, 15231, 15230, true, 0, "mineable/axe"),
            new Block(707, "Stripped Crimson Stem", "stripped_crimson_stem", 2, 2, 15232, 15234, 15233, true, 0, "mineable/axe"),
            new Block(708, "Crimson Hyphae", "crimson_hyphae", 2, 2, 15235, 15237, 15236, true, 0, "mineable/axe"),
            new Block(709, "Stripped Crimson Hyphae", "stripped_crimson_hyphae", 2, 2, 15238, 15240, 15239, true, 0, "mineable/axe"),
            new Block(710, "Crimson Nylium", "crimson_nylium", 0.4, 0.4, 15241, 15241, 15241, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(711, "Crimson Fungus", "crimson_fungus", 0.0, 0.0, 15242, 15242, 15242, true, 0, "plant;mineable/axe"),
            new Block(712, "Shroomlight", "shroomlight", 1, 1, 15243, 15243, 15243, true, 15, "mineable/hoe"),
            new Block(713, "Weeping Vines", "weeping_vines", 0.0, 0.0, 15244, 15269, 15244, true, 0, "plant;mineable/axe"),
            new Block(714, "Weeping Vines Plant", "weeping_vines_plant", 0.0, 0.0, 15270, 15270, 15270, true, 0, "plant;mineable/axe"),
            new Block(715, "Twisting Vines", "twisting_vines", 0.0, 0.0, 15271, 15296, 15271, true, 0, "plant;mineable/axe"),
            new Block(716, "Twisting Vines Plant", "twisting_vines_plant", 0.0, 0.0, 15297, 15297, 15297, true, 0, "plant;mineable/axe"),
            new Block(717, "Crimson Roots", "crimson_roots", 0.0, 0.0, 15298, 15298, 15298, true, 0, "default"),
            new Block(718, "Crimson Planks", "crimson_planks", 2, 3, 15299, 15299, 15299, true, 0, "mineable/axe"),
            new Block(719, "Warped Planks", "warped_planks", 2, 3, 15300, 15300, 15300, true, 0, "mineable/axe"),
            new Block(720, "Crimson Slab", "crimson_slab", 2, 3, 15301, 15306, 15304, true, 0, "mineable/axe"),
            new Block(721, "Warped Slab", "warped_slab", 2, 3, 15307, 15312, 15310, true, 0, "mineable/axe"),
            new Block(722, "Crimson Pressure Plate", "crimson_pressure_plate", 0.5, 0.5, 15313, 15314, 15314, true, 0, "mineable/axe"),
            new Block(723, "Warped Pressure Plate", "warped_pressure_plate", 0.5, 0.5, 15315, 15316, 15316, true, 0, "mineable/axe"),
            new Block(724, "Crimson Fence", "crimson_fence", 2, 3, 15317, 15348, 15348, true, 0, "mineable/axe"),
            new Block(725, "Warped Fence", "warped_fence", 2, 3, 15349, 15380, 15380, true, 0, "mineable/axe"),
            new Block(726, "Crimson Trapdoor", "crimson_trapdoor", 3, 3, 15381, 15444, 15396, true, 0, "mineable/axe"),
            new Block(727, "Warped Trapdoor", "warped_trapdoor", 3, 3, 15445, 15508, 15460, true, 0, "mineable/axe"),
            new Block(728, "Crimson Fence Gate", "crimson_fence_gate", 2, 3, 15509, 15540, 15516, true, 0, "mineable/axe"),
            new Block(729, "Warped Fence Gate", "warped_fence_gate", 2, 3, 15541, 15572, 15548, true, 0, "mineable/axe"),
            new Block(730, "Crimson Stairs", "crimson_stairs", 2, 3, 15573, 15652, 15584, true, 0, "mineable/axe"),
            new Block(731, "Warped Stairs", "warped_stairs", 2, 3, 15653, 15732, 15664, true, 0, "mineable/axe"),
            new Block(732, "Crimson Button", "crimson_button", 0.5, 0.5, 15733, 15756, 15742, true, 0, "mineable/axe"),
            new Block(733, "Warped Button", "warped_button", 0.5, 0.5, 15757, 15780, 15766, true, 0, "mineable/axe"),
            new Block(734, "Crimson Door", "crimson_door", 3, 3, 15781, 15844, 15792, true, 0, "mineable/axe"),
            new Block(735, "Warped Door", "warped_door", 3, 3, 15845, 15908, 15856, true, 0, "mineable/axe"),
            new Block(736, "Crimson Sign", "crimson_sign", 1, 1, 15909, 15940, 15910, true, 0, "mineable/axe"),
            new Block(737, "Warped Sign", "warped_sign", 1, 1, 15941, 15972, 15942, true, 0, "mineable/axe"),
            new Block(738, "Crimson Wall Sign", "crimson_wall_sign", 1, 1, 15973, 15980, 15974, true, 0, "mineable/axe"),
            new Block(739, "Warped Wall Sign", "warped_wall_sign", 1, 1, 15981, 15988, 15982, true, 0, "mineable/axe"),
            new Block(740, "Structure Block", "structure_block", 0.0, 3600000, 15989, 15992, 15990, false, 0, "default", new int[]{}),
            new Block(741, "Jigsaw Block", "jigsaw", 0.0, 3600000, 15993, 16004, 16003, false, 0, "default", new int[]{}),
            new Block(742, "Composter", "composter", 0.6, 0.6, 16005, 16013, 16005, true, 0, "mineable/axe"),
            new Block(743, "Target", "target", 0.5, 0.5, 16014, 16029, 16014, true, 0, "mineable/hoe"),
            new Block(744, "Bee Nest", "bee_nest", 0.3, 0.3, 16030, 16053, 16030, true, 0, "mineable/axe"),
            new Block(745, "Beehive", "beehive", 0.6, 0.6, 16054, 16077, 16054, true, 0, "mineable/axe"),
            new Block(746, "Honey Block", "honey_block", 0.0, 0.0, 16078, 16078, 16078, true, 0, "default"),
            new Block(747, "Honeycomb Block", "honeycomb_block", 0.6, 0.6, 16079, 16079, 16079, true, 0, "default"),
            new Block(748, "Block of Netherite", "netherite_block", 50, 1200, 16080, 16080, 16080, true, 0, "mineable/pickaxe", new int[]{721, 72}),
            new Block(749, "Ancient Debris", "ancient_debris", 30, 1200, 16081, 16081, 16081, true, 0, "mineable/pickaxe", new int[]{721, 72}),
            new Block(750, "Crying Obsidian", "crying_obsidian", 50, 1200, 16082, 16082, 16082, true, 10, "mineable/pickaxe", new int[]{721, 72}),
            new Block(751, "Respawn Anchor", "respawn_anchor", 50, 1200, 16083, 16087, 16083, true, 0, "mineable/pickaxe", new int[]{721, 72}),
            new Block(752, "Potted Crimson Fungus", "potted_crimson_fungus", 0.0, 0.0, 16088, 16088, 16088, true, 0, "default"),
            new Block(753, "Potted Warped Fungus", "potted_warped_fungus", 0.0, 0.0, 16089, 16089, 16089, true, 0, "default"),
            new Block(754, "Potted Crimson Roots", "potted_crimson_roots", 0.0, 0.0, 16090, 16090, 16090, true, 0, "default"),
            new Block(755, "Potted Warped Roots", "potted_warped_roots", 0.0, 0.0, 16091, 16091, 16091, true, 0, "default"),
            new Block(756, "Lodestone", "lodestone", 3.5, 3.5, 16092, 16092, 16092, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(757, "Blackstone", "blackstone", 1.5, 6, 16093, 16093, 16093, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(758, "Blackstone Stairs", "blackstone_stairs", 1.5, 6, 16094, 16173, 16105, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(759, "Blackstone Wall", "blackstone_wall", 1.5, 6, 16174, 16497, 16177, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(760, "Blackstone Slab", "blackstone_slab", 2, 6, 16498, 16503, 16501, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(761, "Polished Blackstone", "polished_blackstone", 2, 6, 16504, 16504, 16504, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(762, "Polished Blackstone Bricks", "polished_blackstone_bricks", 1.5, 6, 16505, 16505, 16505, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(763, "Cracked Polished Blackstone Bricks", "cracked_polished_blackstone_bricks", 1.5, 6, 16506, 16506, 16506, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(764, "Chiseled Polished Blackstone", "chiseled_polished_blackstone", 1.5, 6, 16507, 16507, 16507, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(765, "Polished Blackstone Brick Slab", "polished_blackstone_brick_slab", 2, 6, 16508, 16513, 16511, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(766, "Polished Blackstone Brick Stairs", "polished_blackstone_brick_stairs", 1.5, 6, 16514, 16593, 16525, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(767, "Polished Blackstone Brick Wall", "polished_blackstone_brick_wall", 1.5, 6, 16594, 16917, 16597, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(768, "Gilded Blackstone", "gilded_blackstone", 1.5, 6, 16918, 16918, 16918, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(769, "Polished Blackstone Stairs", "polished_blackstone_stairs", 2, 6, 16919, 16998, 16930, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(770, "Polished Blackstone Slab", "polished_blackstone_slab", 2, 6, 16999, 17004, 17002, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(771, "Polished Blackstone Pressure Plate", "polished_blackstone_pressure_plate", 0.5, 0.5, 17005, 17006, 17006, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(772, "Polished Blackstone Button", "polished_blackstone_button", 0.5, 0.5, 17007, 17030, 17016, true, 0, "default"),
            new Block(773, "Polished Blackstone Wall", "polished_blackstone_wall", 2, 6, 17031, 17354, 17034, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(774, "Chiseled Nether Bricks", "chiseled_nether_bricks", 2, 6, 17355, 17355, 17355, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(775, "Cracked Nether Bricks", "cracked_nether_bricks", 2, 6, 17356, 17356, 17356, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(776, "Quartz Bricks", "quartz_bricks", 0.8, 0.8, 17357, 17357, 17357, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(777, "Candle", "candle", 0.1, 0.1, 17358, 17373, 17361, true, 0, "default"),
            new Block(778, "White Candle", "white_candle", 0.1, 0.1, 17374, 17389, 17377, true, 0, "default"),
            new Block(779, "Orange Candle", "orange_candle", 0.1, 0.1, 17390, 17405, 17393, true, 0, "default"),
            new Block(780, "Magenta Candle", "magenta_candle", 0.1, 0.1, 17406, 17421, 17409, true, 0, "default"),
            new Block(781, "Light Blue Candle", "light_blue_candle", 0.1, 0.1, 17422, 17437, 17425, true, 0, "default"),
            new Block(782, "Yellow Candle", "yellow_candle", 0.1, 0.1, 17438, 17453, 17441, true, 0, "default"),
            new Block(783, "Lime Candle", "lime_candle", 0.1, 0.1, 17454, 17469, 17457, true, 0, "default"),
            new Block(784, "Pink Candle", "pink_candle", 0.1, 0.1, 17470, 17485, 17473, true, 0, "default"),
            new Block(785, "Gray Candle", "gray_candle", 0.1, 0.1, 17486, 17501, 17489, true, 0, "default"),
            new Block(786, "Light Gray Candle", "light_gray_candle", 0.1, 0.1, 17502, 17517, 17505, true, 0, "default"),
            new Block(787, "Cyan Candle", "cyan_candle", 0.1, 0.1, 17518, 17533, 17521, true, 0, "default"),
            new Block(788, "Purple Candle", "purple_candle", 0.1, 0.1, 17534, 17549, 17537, true, 0, "default"),
            new Block(789, "Blue Candle", "blue_candle", 0.1, 0.1, 17550, 17565, 17553, true, 0, "default"),
            new Block(790, "Brown Candle", "brown_candle", 0.1, 0.1, 17566, 17581, 17569, true, 0, "default"),
            new Block(791, "Green Candle", "green_candle", 0.1, 0.1, 17582, 17597, 17585, true, 0, "default"),
            new Block(792, "Red Candle", "red_candle", 0.1, 0.1, 17598, 17613, 17601, true, 0, "default"),
            new Block(793, "Black Candle", "black_candle", 0.1, 0.1, 17614, 17629, 17617, true, 0, "default"),
            new Block(794, "Cake with Candle", "candle_cake", 0.0, 0.0, 17630, 17631, 17631, true, 0, "default"),
            new Block(795, "Cake with White Candle", "white_candle_cake", 0.0, 0.0, 17632, 17633, 17633, true, 0, "default"),
            new Block(796, "Cake with Orange Candle", "orange_candle_cake", 0.0, 0.0, 17634, 17635, 17635, true, 0, "default"),
            new Block(797, "Cake with Magenta Candle", "magenta_candle_cake", 0.0, 0.0, 17636, 17637, 17637, true, 0, "default"),
            new Block(798, "Cake with Light Blue Candle", "light_blue_candle_cake", 0.0, 0.0, 17638, 17639, 17639, true, 0, "default"),
            new Block(799, "Cake with Yellow Candle", "yellow_candle_cake", 0.0, 0.0, 17640, 17641, 17641, true, 0, "default"),
            new Block(800, "Cake with Lime Candle", "lime_candle_cake", 0.0, 0.0, 17642, 17643, 17643, true, 0, "default"),
            new Block(801, "Cake with Pink Candle", "pink_candle_cake", 0.0, 0.0, 17644, 17645, 17645, true, 0, "default"),
            new Block(802, "Cake with Gray Candle", "gray_candle_cake", 0.0, 0.0, 17646, 17647, 17647, true, 0, "default"),
            new Block(803, "Cake with Light Gray Candle", "light_gray_candle_cake", 0.0, 0.0, 17648, 17649, 17649, true, 0, "default"),
            new Block(804, "Cake with Cyan Candle", "cyan_candle_cake", 0.0, 0.0, 17650, 17651, 17651, true, 0, "default"),
            new Block(805, "Cake with Purple Candle", "purple_candle_cake", 0.0, 0.0, 17652, 17653, 17653, true, 0, "default"),
            new Block(806, "Cake with Blue Candle", "blue_candle_cake", 0.0, 0.0, 17654, 17655, 17655, true, 0, "default"),
            new Block(807, "Cake with Brown Candle", "brown_candle_cake", 0.0, 0.0, 17656, 17657, 17657, true, 0, "default"),
            new Block(808, "Cake with Green Candle", "green_candle_cake", 0.0, 0.0, 17658, 17659, 17659, true, 0, "default"),
            new Block(809, "Cake with Red Candle", "red_candle_cake", 0.0, 0.0, 17660, 17661, 17661, true, 0, "default"),
            new Block(810, "Cake with Black Candle", "black_candle_cake", 0.0, 0.0, 17662, 17663, 17663, true, 0, "default"),
            new Block(811, "Block of Amethyst", "amethyst_block", 1.5, 1.5, 17664, 17664, 17664, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(812, "Budding Amethyst", "budding_amethyst", 1.5, 1.5, 17665, 17665, 17665, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(813, "Amethyst Cluster", "amethyst_cluster", 1.5, 1.5, 17666, 17677, 17675, true, 5, "mineable/pickaxe"),
            new Block(814, "Large Amethyst Bud", "large_amethyst_bud", 0.0, 0.0, 17678, 17689, 17687, true, 4, "mineable/pickaxe"),
            new Block(815, "Medium Amethyst Bud", "medium_amethyst_bud", 0.0, 0.0, 17690, 17701, 17699, true, 2, "mineable/pickaxe"),
            new Block(816, "Small Amethyst Bud", "small_amethyst_bud", 0.0, 0.0, 17702, 17713, 17711, true, 1, "mineable/pickaxe"),
            new Block(817, "Tuff", "tuff", 1.5, 6, 17714, 17714, 17714, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(818, "Calcite", "calcite", 0.75, 0.75, 17715, 17715, 17715, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(819, "Tinted Glass", "tinted_glass", 0.0, 0.0, 17716, 17716, 17716, true, 0, "default"),
            new Block(820, "Powder Snow", "powder_snow", 0.25, 0.25, 17717, 17717, 17717, true, 0, "default"),
            new Block(821, "Sculk Sensor", "sculk_sensor", 1.5, 1.5, 17718, 17813, 17719, true, 1, "mineable/hoe"),
            new Block(822, "Oxidized Copper", "oxidized_copper", 3, 6, 17814, 17814, 17814, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(823, "Weathered Copper", "weathered_copper", 3, 6, 17815, 17815, 17815, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(824, "Exposed Copper", "exposed_copper", 3, 6, 17816, 17816, 17816, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(825, "Block of Copper", "copper_block", 3, 6, 17817, 17817, 17817, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(826, "Copper Ore", "copper_ore", 0.0, 0.0, 17818, 17818, 17818, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(827, "Deepslate Copper Ore", "deepslate_copper_ore", 4.5, 3, 17819, 17819, 17819, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(828, "Oxidized Cut Copper", "oxidized_cut_copper", 0.0, 0.0, 17820, 17820, 17820, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(829, "Weathered Cut Copper", "weathered_cut_copper", 0.0, 0.0, 17821, 17821, 17821, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(830, "Exposed Cut Copper", "exposed_cut_copper", 0.0, 0.0, 17822, 17822, 17822, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(831, "Cut Copper", "cut_copper", 0.0, 0.0, 17823, 17823, 17823, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(832, "Oxidized Cut Copper Stairs", "oxidized_cut_copper_stairs", 0.0, 0.0, 17824, 17903, 17835, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(833, "Weathered Cut Copper Stairs", "weathered_cut_copper_stairs", 0.0, 0.0, 17904, 17983, 17915, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(834, "Exposed Cut Copper Stairs", "exposed_cut_copper_stairs", 0.0, 0.0, 17984, 18063, 17995, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(835, "Cut Copper Stairs", "cut_copper_stairs", 0.0, 0.0, 18064, 18143, 18075, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(836, "Oxidized Cut Copper Slab", "oxidized_cut_copper_slab", 0.0, 0.0, 18144, 18149, 18147, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(837, "Weathered Cut Copper Slab", "weathered_cut_copper_slab", 0.0, 0.0, 18150, 18155, 18153, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(838, "Exposed Cut Copper Slab", "exposed_cut_copper_slab", 0.0, 0.0, 18156, 18161, 18159, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(839, "Cut Copper Slab", "cut_copper_slab", 0.0, 0.0, 18162, 18167, 18165, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(840, "Waxed Block of Copper", "waxed_copper_block", 0.0, 0.0, 18168, 18168, 18168, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(841, "Waxed Weathered Copper", "waxed_weathered_copper", 0.0, 0.0, 18169, 18169, 18169, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(842, "Waxed Exposed Copper", "waxed_exposed_copper", 0.0, 0.0, 18170, 18170, 18170, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(843, "Waxed Oxidized Copper", "waxed_oxidized_copper", 0.0, 0.0, 18171, 18171, 18171, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(844, "Waxed Oxidized Cut Copper", "waxed_oxidized_cut_copper", 0.0, 0.0, 18172, 18172, 18172, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(845, "Waxed Weathered Cut Copper", "waxed_weathered_cut_copper", 0.0, 0.0, 18173, 18173, 18173, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(846, "Waxed Exposed Cut Copper", "waxed_exposed_cut_copper", 0.0, 0.0, 18174, 18174, 18174, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(847, "Waxed Cut Copper", "waxed_cut_copper", 0.0, 0.0, 18175, 18175, 18175, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(848, "Waxed Oxidized Cut Copper Stairs", "waxed_oxidized_cut_copper_stairs", 0.0, 0.0, 18176, 18255, 18187, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(849, "Waxed Weathered Cut Copper Stairs", "waxed_weathered_cut_copper_stairs", 0.0, 0.0, 18256, 18335, 18267, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(850, "Waxed Exposed Cut Copper Stairs", "waxed_exposed_cut_copper_stairs", 0.0, 0.0, 18336, 18415, 18347, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(851, "Waxed Cut Copper Stairs", "waxed_cut_copper_stairs", 0.0, 0.0, 18416, 18495, 18427, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(852, "Waxed Oxidized Cut Copper Slab", "waxed_oxidized_cut_copper_slab", 0.0, 0.0, 18496, 18501, 18499, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(853, "Waxed Weathered Cut Copper Slab", "waxed_weathered_cut_copper_slab", 0.0, 0.0, 18502, 18507, 18505, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(854, "Waxed Exposed Cut Copper Slab", "waxed_exposed_cut_copper_slab", 0.0, 0.0, 18508, 18513, 18511, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(855, "Waxed Cut Copper Slab", "waxed_cut_copper_slab", 0.0, 0.0, 18514, 18519, 18517, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(856, "Lightning Rod", "lightning_rod", 3, 6, 18520, 18543, 18539, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(857, "Pointed Dripstone", "pointed_dripstone", 1.5, 3, 18544, 18563, 18549, true, 0, "mineable/pickaxe"),
            new Block(858, "Dripstone Block", "dripstone_block", 1.5, 1, 18564, 18564, 18564, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(859, "Cave Vines", "cave_vines", 0.0, 0.0, 18565, 18616, 18566, true, 0, "plant;mineable/axe"),
            new Block(860, "Cave Vines Plant", "cave_vines_plant", 0.0, 0.0, 18617, 18618, 18618, true, 0, "plant;mineable/axe"),
            new Block(861, "Spore Blossom", "spore_blossom", 0.0, 0.0, 18619, 18619, 18619, true, 0, "plant;mineable/axe"),
            new Block(862, "Azalea", "azalea", 0.0, 0.0, 18620, 18620, 18620, true, 0, "plant;mineable/axe"),
            new Block(863, "Flowering Azalea", "flowering_azalea", 0.0, 0.0, 18621, 18621, 18621, true, 0, "plant;mineable/axe"),
            new Block(864, "Moss Carpet", "moss_carpet", 0.1, 0.1, 18622, 18622, 18622, true, 0, "plant"),
            new Block(865, "Moss Block", "moss_block", 0.1, 0.1, 18623, 18623, 18623, true, 0, "mineable/hoe"),
            new Block(866, "Big Dripleaf", "big_dripleaf", 0.1, 0.1, 18624, 18655, 18625, true, 0, "plant;mineable/axe"),
            new Block(867, "Big Dripleaf Stem", "big_dripleaf_stem", 0.1, 0.1, 18656, 18663, 18657, true, 0, "plant;mineable/axe"),
            new Block(868, "Small Dripleaf", "small_dripleaf", 0.0, 0.0, 18664, 18679, 18667, true, 0, "plant;mineable/axe"),
            new Block(869, "Hanging Roots", "hanging_roots", 0.0, 0.0, 18680, 18681, 18681, true, 0, "plant;mineable/axe"),
            new Block(870, "Rooted Dirt", "rooted_dirt", 0.5, 0.5, 18682, 18682, 18682, true, 0, "mineable/shovel"),
            new Block(871, "Deepslate", "deepslate", 3, 6, 18683, 18685, 18684, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(872, "Cobbled Deepslate", "cobbled_deepslate", 3.5, 6, 18686, 18686, 18686, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(873, "Cobbled Deepslate Stairs", "cobbled_deepslate_stairs", 0.0, 0.0, 18687, 18766, 18698, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(874, "Cobbled Deepslate Slab", "cobbled_deepslate_slab", 0.0, 0.0, 18767, 18772, 18770, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(875, "Cobbled Deepslate Wall", "cobbled_deepslate_wall", 0.0, 0.0, 18773, 19096, 18776, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(876, "Polished Deepslate", "polished_deepslate", 0.0, 0.0, 19097, 19097, 19097, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(877, "Polished Deepslate Stairs", "polished_deepslate_stairs", 0.0, 0.0, 19098, 19177, 19109, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(878, "Polished Deepslate Slab", "polished_deepslate_slab", 0.0, 0.0, 19178, 19183, 19181, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(879, "Polished Deepslate Wall", "polished_deepslate_wall", 0.0, 0.0, 19184, 19507, 19187, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(880, "Deepslate Tiles", "deepslate_tiles", 0.0, 0.0, 19508, 19508, 19508, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(881, "Deepslate Tile Stairs", "deepslate_tile_stairs", 0.0, 0.0, 19509, 19588, 19520, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(882, "Deepslate Tile Slab", "deepslate_tile_slab", 0.0, 0.0, 19589, 19594, 19592, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(883, "Deepslate Tile Wall", "deepslate_tile_wall", 0.0, 0.0, 19595, 19918, 19598, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(884, "Deepslate Bricks", "deepslate_bricks", 0.0, 0.0, 19919, 19919, 19919, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(885, "Deepslate Brick Stairs", "deepslate_brick_stairs", 0.0, 0.0, 19920, 19999, 19931, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(886, "Deepslate Brick Slab", "deepslate_brick_slab", 0.0, 0.0, 20000, 20005, 20003, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(887, "Deepslate Brick Wall", "deepslate_brick_wall", 0.0, 0.0, 20006, 20329, 20009, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(888, "Chiseled Deepslate", "chiseled_deepslate", 0.0, 0.0, 20330, 20330, 20330, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(889, "Cracked Deepslate Bricks", "cracked_deepslate_bricks", 0.0, 0.0, 20331, 20331, 20331, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(890, "Cracked Deepslate Tiles", "cracked_deepslate_tiles", 0.0, 0.0, 20332, 20332, 20332, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(891, "Infested Deepslate", "infested_deepslate", 0.0, 0.0, 20333, 20335, 20334, true, 0, "mineable/pickaxe"),
            new Block(892, "Smooth Basalt", "smooth_basalt", 0.0, 0.0, 20336, 20336, 20336, true, 0, "mineable/pickaxe", new int[]{701, 706, 711, 716, 721, 72}),
            new Block(893, "Block of Raw Iron", "raw_iron_block", 5, 6, 20337, 20337, 20337, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(894, "Block of Raw Copper", "raw_copper_block", 5, 6, 20338, 20338, 20338, true, 0, "mineable/pickaxe", new int[]{706, 716, 721, 72}),
            new Block(895, "Block of Raw Gold", "raw_gold_block", 5, 6, 20339, 20339, 20339, true, 0, "mineable/pickaxe", new int[]{716, 721, 72}),
            new Block(896, "Potted Azalea", "potted_azalea_bush", 0.0, 0.0, 20340, 20340, 20340, true, 0, "default"),
            new Block(897, "Potted Flowering Azalea", "potted_flowering_azalea_bush", 0.0, 0.0, 20341, 20341, 20341, true, 0, "default")
            // End auto generated code
    ));

    // Begin auto generated code
    public static final Block AIR = getBlock(0);
    public static final Block STONE = getBlock(1);
    public static final Block GRANITE = getBlock(2);
    public static final Block POLISHED_GRANITE = getBlock(3);
    public static final Block DIORITE = getBlock(4);
    public static final Block POLISHED_DIORITE = getBlock(5);
    public static final Block ANDESITE = getBlock(6);
    public static final Block POLISHED_ANDESITE = getBlock(7);
    public static final Block GRASS_BLOCK = getBlock(8);
    public static final Block DIRT = getBlock(9);
    public static final Block COARSE_DIRT = getBlock(10);
    public static final Block PODZOL = getBlock(11);
    public static final Block COBBLESTONE = getBlock(12);
    public static final Block OAK_PLANKS = getBlock(13);
    public static final Block SPRUCE_PLANKS = getBlock(14);
    public static final Block BIRCH_PLANKS = getBlock(15);
    public static final Block JUNGLE_PLANKS = getBlock(16);
    public static final Block ACACIA_PLANKS = getBlock(17);
    public static final Block DARK_OAK_PLANKS = getBlock(18);
    public static final Block OAK_SAPLING = getBlock(19);
    public static final Block SPRUCE_SAPLING = getBlock(20);
    public static final Block BIRCH_SAPLING = getBlock(21);
    public static final Block JUNGLE_SAPLING = getBlock(22);
    public static final Block ACACIA_SAPLING = getBlock(23);
    public static final Block DARK_OAK_SAPLING = getBlock(24);
    public static final Block BEDROCK = getBlock(25);
    public static final Block WATER = getBlock(26);
    public static final Block LAVA = getBlock(27);
    public static final Block SAND = getBlock(28);
    public static final Block RED_SAND = getBlock(29);
    public static final Block GRAVEL = getBlock(30);
    public static final Block GOLD_ORE = getBlock(31);
    public static final Block DEEPSLATE_GOLD_ORE = getBlock(32);
    public static final Block IRON_ORE = getBlock(33);
    public static final Block DEEPSLATE_IRON_ORE = getBlock(34);
    public static final Block COAL_ORE = getBlock(35);
    public static final Block DEEPSLATE_COAL_ORE = getBlock(36);
    public static final Block NETHER_GOLD_ORE = getBlock(37);
    public static final Block OAK_LOG = getBlock(38);
    public static final Block SPRUCE_LOG = getBlock(39);
    public static final Block BIRCH_LOG = getBlock(40);
    public static final Block JUNGLE_LOG = getBlock(41);
    public static final Block ACACIA_LOG = getBlock(42);
    public static final Block DARK_OAK_LOG = getBlock(43);
    public static final Block STRIPPED_SPRUCE_LOG = getBlock(44);
    public static final Block STRIPPED_BIRCH_LOG = getBlock(45);
    public static final Block STRIPPED_JUNGLE_LOG = getBlock(46);
    public static final Block STRIPPED_ACACIA_LOG = getBlock(47);
    public static final Block STRIPPED_DARK_OAK_LOG = getBlock(48);
    public static final Block STRIPPED_OAK_LOG = getBlock(49);
    public static final Block OAK_WOOD = getBlock(50);
    public static final Block SPRUCE_WOOD = getBlock(51);
    public static final Block BIRCH_WOOD = getBlock(52);
    public static final Block JUNGLE_WOOD = getBlock(53);
    public static final Block ACACIA_WOOD = getBlock(54);
    public static final Block DARK_OAK_WOOD = getBlock(55);
    public static final Block STRIPPED_OAK_WOOD = getBlock(56);
    public static final Block STRIPPED_SPRUCE_WOOD = getBlock(57);
    public static final Block STRIPPED_BIRCH_WOOD = getBlock(58);
    public static final Block STRIPPED_JUNGLE_WOOD = getBlock(59);
    public static final Block STRIPPED_ACACIA_WOOD = getBlock(60);
    public static final Block STRIPPED_DARK_OAK_WOOD = getBlock(61);
    public static final Block OAK_LEAVES = getBlock(62);
    public static final Block SPRUCE_LEAVES = getBlock(63);
    public static final Block BIRCH_LEAVES = getBlock(64);
    public static final Block JUNGLE_LEAVES = getBlock(65);
    public static final Block ACACIA_LEAVES = getBlock(66);
    public static final Block DARK_OAK_LEAVES = getBlock(67);
    public static final Block AZALEA_LEAVES = getBlock(68);
    public static final Block FLOWERING_AZALEA_LEAVES = getBlock(69);
    public static final Block SPONGE = getBlock(70);
    public static final Block WET_SPONGE = getBlock(71);
    public static final Block GLASS = getBlock(72);
    public static final Block LAPIS_ORE = getBlock(73);
    public static final Block DEEPSLATE_LAPIS_ORE = getBlock(74);
    public static final Block LAPIS_BLOCK = getBlock(75);
    public static final Block DISPENSER = getBlock(76);
    public static final Block SANDSTONE = getBlock(77);
    public static final Block CHISELED_SANDSTONE = getBlock(78);
    public static final Block CUT_SANDSTONE = getBlock(79);
    public static final Block NOTE_BLOCK = getBlock(80);
    public static final Block WHITE_BED = getBlock(81);
    public static final Block ORANGE_BED = getBlock(82);
    public static final Block MAGENTA_BED = getBlock(83);
    public static final Block LIGHT_BLUE_BED = getBlock(84);
    public static final Block YELLOW_BED = getBlock(85);
    public static final Block LIME_BED = getBlock(86);
    public static final Block PINK_BED = getBlock(87);
    public static final Block GRAY_BED = getBlock(88);
    public static final Block LIGHT_GRAY_BED = getBlock(89);
    public static final Block CYAN_BED = getBlock(90);
    public static final Block PURPLE_BED = getBlock(91);
    public static final Block BLUE_BED = getBlock(92);
    public static final Block BROWN_BED = getBlock(93);
    public static final Block GREEN_BED = getBlock(94);
    public static final Block RED_BED = getBlock(95);
    public static final Block BLACK_BED = getBlock(96);
    public static final Block POWERED_RAIL = getBlock(97);
    public static final Block DETECTOR_RAIL = getBlock(98);
    public static final Block STICKY_PISTON = getBlock(99);
    public static final Block COBWEB = getBlock(100);
    public static final Block GRASS = getBlock(101);
    public static final Block FERN = getBlock(102);
    public static final Block DEAD_BUSH = getBlock(103);
    public static final Block SEAGRASS = getBlock(104);
    public static final Block TALL_SEAGRASS = getBlock(105);
    public static final Block PISTON = getBlock(106);
    public static final Block PISTON_HEAD = getBlock(107);
    public static final Block WHITE_WOOL = getBlock(108);
    public static final Block ORANGE_WOOL = getBlock(109);
    public static final Block MAGENTA_WOOL = getBlock(110);
    public static final Block LIGHT_BLUE_WOOL = getBlock(111);
    public static final Block YELLOW_WOOL = getBlock(112);
    public static final Block LIME_WOOL = getBlock(113);
    public static final Block PINK_WOOL = getBlock(114);
    public static final Block GRAY_WOOL = getBlock(115);
    public static final Block LIGHT_GRAY_WOOL = getBlock(116);
    public static final Block CYAN_WOOL = getBlock(117);
    public static final Block PURPLE_WOOL = getBlock(118);
    public static final Block BLUE_WOOL = getBlock(119);
    public static final Block BROWN_WOOL = getBlock(120);
    public static final Block GREEN_WOOL = getBlock(121);
    public static final Block RED_WOOL = getBlock(122);
    public static final Block BLACK_WOOL = getBlock(123);
    public static final Block MOVING_PISTON = getBlock(124);
    public static final Block DANDELION = getBlock(125);
    public static final Block POPPY = getBlock(126);
    public static final Block BLUE_ORCHID = getBlock(127);
    public static final Block ALLIUM = getBlock(128);
    public static final Block AZURE_BLUET = getBlock(129);
    public static final Block RED_TULIP = getBlock(130);
    public static final Block ORANGE_TULIP = getBlock(131);
    public static final Block WHITE_TULIP = getBlock(132);
    public static final Block PINK_TULIP = getBlock(133);
    public static final Block OXEYE_DAISY = getBlock(134);
    public static final Block CORNFLOWER = getBlock(135);
    public static final Block WITHER_ROSE = getBlock(136);
    public static final Block LILY_OF_THE_VALLEY = getBlock(137);
    public static final Block BROWN_MUSHROOM = getBlock(138);
    public static final Block RED_MUSHROOM = getBlock(139);
    public static final Block GOLD_BLOCK = getBlock(140);
    public static final Block IRON_BLOCK = getBlock(141);
    public static final Block BRICKS = getBlock(142);
    public static final Block TNT = getBlock(143);
    public static final Block BOOKSHELF = getBlock(144);
    public static final Block MOSSY_COBBLESTONE = getBlock(145);
    public static final Block OBSIDIAN = getBlock(146);
    public static final Block TORCH = getBlock(147);
    public static final Block WALL_TORCH = getBlock(148);
    public static final Block FIRE = getBlock(149);
    public static final Block SOUL_FIRE = getBlock(150);
    public static final Block SPAWNER = getBlock(151);
    public static final Block OAK_STAIRS = getBlock(152);
    public static final Block CHEST = getBlock(153);
    public static final Block REDSTONE_WIRE = getBlock(154);
    public static final Block DIAMOND_ORE = getBlock(155);
    public static final Block DEEPSLATE_DIAMOND_ORE = getBlock(156);
    public static final Block DIAMOND_BLOCK = getBlock(157);
    public static final Block CRAFTING_TABLE = getBlock(158);
    public static final Block WHEAT = getBlock(159);
    public static final Block FARMLAND = getBlock(160);
    public static final Block FURNACE = getBlock(161);
    public static final Block OAK_SIGN = getBlock(162);
    public static final Block SPRUCE_SIGN = getBlock(163);
    public static final Block BIRCH_SIGN = getBlock(164);
    public static final Block ACACIA_SIGN = getBlock(165);
    public static final Block JUNGLE_SIGN = getBlock(166);
    public static final Block DARK_OAK_SIGN = getBlock(167);
    public static final Block OAK_DOOR = getBlock(168);
    public static final Block LADDER = getBlock(169);
    public static final Block RAIL = getBlock(170);
    public static final Block COBBLESTONE_STAIRS = getBlock(171);
    public static final Block OAK_WALL_SIGN = getBlock(172);
    public static final Block SPRUCE_WALL_SIGN = getBlock(173);
    public static final Block BIRCH_WALL_SIGN = getBlock(174);
    public static final Block ACACIA_WALL_SIGN = getBlock(175);
    public static final Block JUNGLE_WALL_SIGN = getBlock(176);
    public static final Block DARK_OAK_WALL_SIGN = getBlock(177);
    public static final Block LEVER = getBlock(178);
    public static final Block STONE_PRESSURE_PLATE = getBlock(179);
    public static final Block IRON_DOOR = getBlock(180);
    public static final Block OAK_PRESSURE_PLATE = getBlock(181);
    public static final Block SPRUCE_PRESSURE_PLATE = getBlock(182);
    public static final Block BIRCH_PRESSURE_PLATE = getBlock(183);
    public static final Block JUNGLE_PRESSURE_PLATE = getBlock(184);
    public static final Block ACACIA_PRESSURE_PLATE = getBlock(185);
    public static final Block DARK_OAK_PRESSURE_PLATE = getBlock(186);
    public static final Block REDSTONE_ORE = getBlock(187);
    public static final Block DEEPSLATE_REDSTONE_ORE = getBlock(188);
    public static final Block REDSTONE_TORCH = getBlock(189);
    public static final Block REDSTONE_WALL_TORCH = getBlock(190);
    public static final Block STONE_BUTTON = getBlock(191);
    public static final Block SNOW = getBlock(192);
    public static final Block ICE = getBlock(193);
    public static final Block SNOW_BLOCK = getBlock(194);
    public static final Block CACTUS = getBlock(195);
    public static final Block CLAY = getBlock(196);
    public static final Block SUGAR_CANE = getBlock(197);
    public static final Block JUKEBOX = getBlock(198);
    public static final Block OAK_FENCE = getBlock(199);
    public static final Block PUMPKIN = getBlock(200);
    public static final Block NETHERRACK = getBlock(201);
    public static final Block SOUL_SAND = getBlock(202);
    public static final Block SOUL_SOIL = getBlock(203);
    public static final Block BASALT = getBlock(204);
    public static final Block POLISHED_BASALT = getBlock(205);
    public static final Block SOUL_TORCH = getBlock(206);
    public static final Block SOUL_WALL_TORCH = getBlock(207);
    public static final Block GLOWSTONE = getBlock(208);
    public static final Block NETHER_PORTAL = getBlock(209);
    public static final Block CARVED_PUMPKIN = getBlock(210);
    public static final Block JACK_O_LANTERN = getBlock(211);
    public static final Block CAKE = getBlock(212);
    public static final Block REPEATER = getBlock(213);
    public static final Block WHITE_STAINED_GLASS = getBlock(214);
    public static final Block ORANGE_STAINED_GLASS = getBlock(215);
    public static final Block MAGENTA_STAINED_GLASS = getBlock(216);
    public static final Block LIGHT_BLUE_STAINED_GLASS = getBlock(217);
    public static final Block YELLOW_STAINED_GLASS = getBlock(218);
    public static final Block LIME_STAINED_GLASS = getBlock(219);
    public static final Block PINK_STAINED_GLASS = getBlock(220);
    public static final Block GRAY_STAINED_GLASS = getBlock(221);
    public static final Block LIGHT_GRAY_STAINED_GLASS = getBlock(222);
    public static final Block CYAN_STAINED_GLASS = getBlock(223);
    public static final Block PURPLE_STAINED_GLASS = getBlock(224);
    public static final Block BLUE_STAINED_GLASS = getBlock(225);
    public static final Block BROWN_STAINED_GLASS = getBlock(226);
    public static final Block GREEN_STAINED_GLASS = getBlock(227);
    public static final Block RED_STAINED_GLASS = getBlock(228);
    public static final Block BLACK_STAINED_GLASS = getBlock(229);
    public static final Block OAK_TRAPDOOR = getBlock(230);
    public static final Block SPRUCE_TRAPDOOR = getBlock(231);
    public static final Block BIRCH_TRAPDOOR = getBlock(232);
    public static final Block JUNGLE_TRAPDOOR = getBlock(233);
    public static final Block ACACIA_TRAPDOOR = getBlock(234);
    public static final Block DARK_OAK_TRAPDOOR = getBlock(235);
    public static final Block STONE_BRICKS = getBlock(236);
    public static final Block MOSSY_STONE_BRICKS = getBlock(237);
    public static final Block CRACKED_STONE_BRICKS = getBlock(238);
    public static final Block CHISELED_STONE_BRICKS = getBlock(239);
    public static final Block INFESTED_STONE = getBlock(240);
    public static final Block INFESTED_COBBLESTONE = getBlock(241);
    public static final Block INFESTED_STONE_BRICKS = getBlock(242);
    public static final Block INFESTED_MOSSY_STONE_BRICKS = getBlock(243);
    public static final Block INFESTED_CRACKED_STONE_BRICKS = getBlock(244);
    public static final Block INFESTED_CHISELED_STONE_BRICKS = getBlock(245);
    public static final Block BROWN_MUSHROOM_BLOCK = getBlock(246);
    public static final Block RED_MUSHROOM_BLOCK = getBlock(247);
    public static final Block MUSHROOM_STEM = getBlock(248);
    public static final Block IRON_BARS = getBlock(249);
    public static final Block CHAIN = getBlock(250);
    public static final Block GLASS_PANE = getBlock(251);
    public static final Block MELON = getBlock(252);
    public static final Block ATTACHED_PUMPKIN_STEM = getBlock(253);
    public static final Block ATTACHED_MELON_STEM = getBlock(254);
    public static final Block PUMPKIN_STEM = getBlock(255);
    public static final Block MELON_STEM = getBlock(256);
    public static final Block VINE = getBlock(257);
    public static final Block GLOW_LICHEN = getBlock(258);
    public static final Block OAK_FENCE_GATE = getBlock(259);
    public static final Block BRICK_STAIRS = getBlock(260);
    public static final Block STONE_BRICK_STAIRS = getBlock(261);
    public static final Block MYCELIUM = getBlock(262);
    public static final Block LILY_PAD = getBlock(263);
    public static final Block NETHER_BRICKS = getBlock(264);
    public static final Block NETHER_BRICK_FENCE = getBlock(265);
    public static final Block NETHER_BRICK_STAIRS = getBlock(266);
    public static final Block NETHER_WART = getBlock(267);
    public static final Block ENCHANTING_TABLE = getBlock(268);
    public static final Block BREWING_STAND = getBlock(269);
    public static final Block CAULDRON = getBlock(270);
    public static final Block WATER_CAULDRON = getBlock(271);
    public static final Block LAVA_CAULDRON = getBlock(272);
    public static final Block POWDER_SNOW_CAULDRON = getBlock(273);
    public static final Block END_PORTAL = getBlock(274);
    public static final Block END_PORTAL_FRAME = getBlock(275);
    public static final Block END_STONE = getBlock(276);
    public static final Block DRAGON_EGG = getBlock(277);
    public static final Block REDSTONE_LAMP = getBlock(278);
    public static final Block COCOA = getBlock(279);
    public static final Block SANDSTONE_STAIRS = getBlock(280);
    public static final Block EMERALD_ORE = getBlock(281);
    public static final Block DEEPSLATE_EMERALD_ORE = getBlock(282);
    public static final Block ENDER_CHEST = getBlock(283);
    public static final Block TRIPWIRE_HOOK = getBlock(284);
    public static final Block TRIPWIRE = getBlock(285);
    public static final Block EMERALD_BLOCK = getBlock(286);
    public static final Block SPRUCE_STAIRS = getBlock(287);
    public static final Block BIRCH_STAIRS = getBlock(288);
    public static final Block JUNGLE_STAIRS = getBlock(289);
    public static final Block COMMAND_BLOCK = getBlock(290);
    public static final Block BEACON = getBlock(291);
    public static final Block COBBLESTONE_WALL = getBlock(292);
    public static final Block MOSSY_COBBLESTONE_WALL = getBlock(293);
    public static final Block FLOWER_POT = getBlock(294);
    public static final Block POTTED_OAK_SAPLING = getBlock(295);
    public static final Block POTTED_SPRUCE_SAPLING = getBlock(296);
    public static final Block POTTED_BIRCH_SAPLING = getBlock(297);
    public static final Block POTTED_JUNGLE_SAPLING = getBlock(298);
    public static final Block POTTED_ACACIA_SAPLING = getBlock(299);
    public static final Block POTTED_DARK_OAK_SAPLING = getBlock(300);
    public static final Block POTTED_FERN = getBlock(301);
    public static final Block POTTED_DANDELION = getBlock(302);
    public static final Block POTTED_POPPY = getBlock(303);
    public static final Block POTTED_BLUE_ORCHID = getBlock(304);
    public static final Block POTTED_ALLIUM = getBlock(305);
    public static final Block POTTED_AZURE_BLUET = getBlock(306);
    public static final Block POTTED_RED_TULIP = getBlock(307);
    public static final Block POTTED_ORANGE_TULIP = getBlock(308);
    public static final Block POTTED_WHITE_TULIP = getBlock(309);
    public static final Block POTTED_PINK_TULIP = getBlock(310);
    public static final Block POTTED_OXEYE_DAISY = getBlock(311);
    public static final Block POTTED_CORNFLOWER = getBlock(312);
    public static final Block POTTED_LILY_OF_THE_VALLEY = getBlock(313);
    public static final Block POTTED_WITHER_ROSE = getBlock(314);
    public static final Block POTTED_RED_MUSHROOM = getBlock(315);
    public static final Block POTTED_BROWN_MUSHROOM = getBlock(316);
    public static final Block POTTED_DEAD_BUSH = getBlock(317);
    public static final Block POTTED_CACTUS = getBlock(318);
    public static final Block CARROTS = getBlock(319);
    public static final Block POTATOES = getBlock(320);
    public static final Block OAK_BUTTON = getBlock(321);
    public static final Block SPRUCE_BUTTON = getBlock(322);
    public static final Block BIRCH_BUTTON = getBlock(323);
    public static final Block JUNGLE_BUTTON = getBlock(324);
    public static final Block ACACIA_BUTTON = getBlock(325);
    public static final Block DARK_OAK_BUTTON = getBlock(326);
    public static final Block SKELETON_SKULL = getBlock(327);
    public static final Block SKELETON_WALL_SKULL = getBlock(328);
    public static final Block WITHER_SKELETON_SKULL = getBlock(329);
    public static final Block WITHER_SKELETON_WALL_SKULL = getBlock(330);
    public static final Block ZOMBIE_HEAD = getBlock(331);
    public static final Block ZOMBIE_WALL_HEAD = getBlock(332);
    public static final Block PLAYER_HEAD = getBlock(333);
    public static final Block PLAYER_WALL_HEAD = getBlock(334);
    public static final Block CREEPER_HEAD = getBlock(335);
    public static final Block CREEPER_WALL_HEAD = getBlock(336);
    public static final Block DRAGON_HEAD = getBlock(337);
    public static final Block DRAGON_WALL_HEAD = getBlock(338);
    public static final Block ANVIL = getBlock(339);
    public static final Block CHIPPED_ANVIL = getBlock(340);
    public static final Block DAMAGED_ANVIL = getBlock(341);
    public static final Block TRAPPED_CHEST = getBlock(342);
    public static final Block LIGHT_WEIGHTED_PRESSURE_PLATE = getBlock(343);
    public static final Block HEAVY_WEIGHTED_PRESSURE_PLATE = getBlock(344);
    public static final Block COMPARATOR = getBlock(345);
    public static final Block DAYLIGHT_DETECTOR = getBlock(346);
    public static final Block REDSTONE_BLOCK = getBlock(347);
    public static final Block NETHER_QUARTZ_ORE = getBlock(348);
    public static final Block HOPPER = getBlock(349);
    public static final Block QUARTZ_BLOCK = getBlock(350);
    public static final Block CHISELED_QUARTZ_BLOCK = getBlock(351);
    public static final Block QUARTZ_PILLAR = getBlock(352);
    public static final Block QUARTZ_STAIRS = getBlock(353);
    public static final Block ACTIVATOR_RAIL = getBlock(354);
    public static final Block DROPPER = getBlock(355);
    public static final Block WHITE_TERRACOTTA = getBlock(356);
    public static final Block ORANGE_TERRACOTTA = getBlock(357);
    public static final Block MAGENTA_TERRACOTTA = getBlock(358);
    public static final Block LIGHT_BLUE_TERRACOTTA = getBlock(359);
    public static final Block YELLOW_TERRACOTTA = getBlock(360);
    public static final Block LIME_TERRACOTTA = getBlock(361);
    public static final Block PINK_TERRACOTTA = getBlock(362);
    public static final Block GRAY_TERRACOTTA = getBlock(363);
    public static final Block LIGHT_GRAY_TERRACOTTA = getBlock(364);
    public static final Block CYAN_TERRACOTTA = getBlock(365);
    public static final Block PURPLE_TERRACOTTA = getBlock(366);
    public static final Block BLUE_TERRACOTTA = getBlock(367);
    public static final Block BROWN_TERRACOTTA = getBlock(368);
    public static final Block GREEN_TERRACOTTA = getBlock(369);
    public static final Block RED_TERRACOTTA = getBlock(370);
    public static final Block BLACK_TERRACOTTA = getBlock(371);
    public static final Block WHITE_STAINED_GLASS_PANE = getBlock(372);
    public static final Block ORANGE_STAINED_GLASS_PANE = getBlock(373);
    public static final Block MAGENTA_STAINED_GLASS_PANE = getBlock(374);
    public static final Block LIGHT_BLUE_STAINED_GLASS_PANE = getBlock(375);
    public static final Block YELLOW_STAINED_GLASS_PANE = getBlock(376);
    public static final Block LIME_STAINED_GLASS_PANE = getBlock(377);
    public static final Block PINK_STAINED_GLASS_PANE = getBlock(378);
    public static final Block GRAY_STAINED_GLASS_PANE = getBlock(379);
    public static final Block LIGHT_GRAY_STAINED_GLASS_PANE = getBlock(380);
    public static final Block CYAN_STAINED_GLASS_PANE = getBlock(381);
    public static final Block PURPLE_STAINED_GLASS_PANE = getBlock(382);
    public static final Block BLUE_STAINED_GLASS_PANE = getBlock(383);
    public static final Block BROWN_STAINED_GLASS_PANE = getBlock(384);
    public static final Block GREEN_STAINED_GLASS_PANE = getBlock(385);
    public static final Block RED_STAINED_GLASS_PANE = getBlock(386);
    public static final Block BLACK_STAINED_GLASS_PANE = getBlock(387);
    public static final Block ACACIA_STAIRS = getBlock(388);
    public static final Block DARK_OAK_STAIRS = getBlock(389);
    public static final Block SLIME_BLOCK = getBlock(390);
    public static final Block BARRIER = getBlock(391);
    public static final Block LIGHT = getBlock(392);
    public static final Block IRON_TRAPDOOR = getBlock(393);
    public static final Block PRISMARINE = getBlock(394);
    public static final Block PRISMARINE_BRICKS = getBlock(395);
    public static final Block DARK_PRISMARINE = getBlock(396);
    public static final Block PRISMARINE_STAIRS = getBlock(397);
    public static final Block PRISMARINE_BRICK_STAIRS = getBlock(398);
    public static final Block DARK_PRISMARINE_STAIRS = getBlock(399);
    public static final Block PRISMARINE_SLAB = getBlock(400);
    public static final Block PRISMARINE_BRICK_SLAB = getBlock(401);
    public static final Block DARK_PRISMARINE_SLAB = getBlock(402);
    public static final Block SEA_LANTERN = getBlock(403);
    public static final Block HAY_BLOCK = getBlock(404);
    public static final Block WHITE_CARPET = getBlock(405);
    public static final Block ORANGE_CARPET = getBlock(406);
    public static final Block MAGENTA_CARPET = getBlock(407);
    public static final Block LIGHT_BLUE_CARPET = getBlock(408);
    public static final Block YELLOW_CARPET = getBlock(409);
    public static final Block LIME_CARPET = getBlock(410);
    public static final Block PINK_CARPET = getBlock(411);
    public static final Block GRAY_CARPET = getBlock(412);
    public static final Block LIGHT_GRAY_CARPET = getBlock(413);
    public static final Block CYAN_CARPET = getBlock(414);
    public static final Block PURPLE_CARPET = getBlock(415);
    public static final Block BLUE_CARPET = getBlock(416);
    public static final Block BROWN_CARPET = getBlock(417);
    public static final Block GREEN_CARPET = getBlock(418);
    public static final Block RED_CARPET = getBlock(419);
    public static final Block BLACK_CARPET = getBlock(420);
    public static final Block TERRACOTTA = getBlock(421);
    public static final Block COAL_BLOCK = getBlock(422);
    public static final Block PACKED_ICE = getBlock(423);
    public static final Block SUNFLOWER = getBlock(424);
    public static final Block LILAC = getBlock(425);
    public static final Block ROSE_BUSH = getBlock(426);
    public static final Block PEONY = getBlock(427);
    public static final Block TALL_GRASS = getBlock(428);
    public static final Block LARGE_FERN = getBlock(429);
    public static final Block WHITE_BANNER = getBlock(430);
    public static final Block ORANGE_BANNER = getBlock(431);
    public static final Block MAGENTA_BANNER = getBlock(432);
    public static final Block LIGHT_BLUE_BANNER = getBlock(433);
    public static final Block YELLOW_BANNER = getBlock(434);
    public static final Block LIME_BANNER = getBlock(435);
    public static final Block PINK_BANNER = getBlock(436);
    public static final Block GRAY_BANNER = getBlock(437);
    public static final Block LIGHT_GRAY_BANNER = getBlock(438);
    public static final Block CYAN_BANNER = getBlock(439);
    public static final Block PURPLE_BANNER = getBlock(440);
    public static final Block BLUE_BANNER = getBlock(441);
    public static final Block BROWN_BANNER = getBlock(442);
    public static final Block GREEN_BANNER = getBlock(443);
    public static final Block RED_BANNER = getBlock(444);
    public static final Block BLACK_BANNER = getBlock(445);
    public static final Block WHITE_WALL_BANNER = getBlock(446);
    public static final Block ORANGE_WALL_BANNER = getBlock(447);
    public static final Block MAGENTA_WALL_BANNER = getBlock(448);
    public static final Block LIGHT_BLUE_WALL_BANNER = getBlock(449);
    public static final Block YELLOW_WALL_BANNER = getBlock(450);
    public static final Block LIME_WALL_BANNER = getBlock(451);
    public static final Block PINK_WALL_BANNER = getBlock(452);
    public static final Block GRAY_WALL_BANNER = getBlock(453);
    public static final Block LIGHT_GRAY_WALL_BANNER = getBlock(454);
    public static final Block CYAN_WALL_BANNER = getBlock(455);
    public static final Block PURPLE_WALL_BANNER = getBlock(456);
    public static final Block BLUE_WALL_BANNER = getBlock(457);
    public static final Block BROWN_WALL_BANNER = getBlock(458);
    public static final Block GREEN_WALL_BANNER = getBlock(459);
    public static final Block RED_WALL_BANNER = getBlock(460);
    public static final Block BLACK_WALL_BANNER = getBlock(461);
    public static final Block RED_SANDSTONE = getBlock(462);
    public static final Block CHISELED_RED_SANDSTONE = getBlock(463);
    public static final Block CUT_RED_SANDSTONE = getBlock(464);
    public static final Block RED_SANDSTONE_STAIRS = getBlock(465);
    public static final Block OAK_SLAB = getBlock(466);
    public static final Block SPRUCE_SLAB = getBlock(467);
    public static final Block BIRCH_SLAB = getBlock(468);
    public static final Block JUNGLE_SLAB = getBlock(469);
    public static final Block ACACIA_SLAB = getBlock(470);
    public static final Block DARK_OAK_SLAB = getBlock(471);
    public static final Block STONE_SLAB = getBlock(472);
    public static final Block SMOOTH_STONE_SLAB = getBlock(473);
    public static final Block SANDSTONE_SLAB = getBlock(474);
    public static final Block CUT_SANDSTONE_SLAB = getBlock(475);
    public static final Block PETRIFIED_OAK_SLAB = getBlock(476);
    public static final Block COBBLESTONE_SLAB = getBlock(477);
    public static final Block BRICK_SLAB = getBlock(478);
    public static final Block STONE_BRICK_SLAB = getBlock(479);
    public static final Block NETHER_BRICK_SLAB = getBlock(480);
    public static final Block QUARTZ_SLAB = getBlock(481);
    public static final Block RED_SANDSTONE_SLAB = getBlock(482);
    public static final Block CUT_RED_SANDSTONE_SLAB = getBlock(483);
    public static final Block PURPUR_SLAB = getBlock(484);
    public static final Block SMOOTH_STONE = getBlock(485);
    public static final Block SMOOTH_SANDSTONE = getBlock(486);
    public static final Block SMOOTH_QUARTZ = getBlock(487);
    public static final Block SMOOTH_RED_SANDSTONE = getBlock(488);
    public static final Block SPRUCE_FENCE_GATE = getBlock(489);
    public static final Block BIRCH_FENCE_GATE = getBlock(490);
    public static final Block JUNGLE_FENCE_GATE = getBlock(491);
    public static final Block ACACIA_FENCE_GATE = getBlock(492);
    public static final Block DARK_OAK_FENCE_GATE = getBlock(493);
    public static final Block SPRUCE_FENCE = getBlock(494);
    public static final Block BIRCH_FENCE = getBlock(495);
    public static final Block JUNGLE_FENCE = getBlock(496);
    public static final Block ACACIA_FENCE = getBlock(497);
    public static final Block DARK_OAK_FENCE = getBlock(498);
    public static final Block SPRUCE_DOOR = getBlock(499);
    public static final Block BIRCH_DOOR = getBlock(500);
    public static final Block JUNGLE_DOOR = getBlock(501);
    public static final Block ACACIA_DOOR = getBlock(502);
    public static final Block DARK_OAK_DOOR = getBlock(503);
    public static final Block END_ROD = getBlock(504);
    public static final Block CHORUS_PLANT = getBlock(505);
    public static final Block CHORUS_FLOWER = getBlock(506);
    public static final Block PURPUR_BLOCK = getBlock(507);
    public static final Block PURPUR_PILLAR = getBlock(508);
    public static final Block PURPUR_STAIRS = getBlock(509);
    public static final Block END_STONE_BRICKS = getBlock(510);
    public static final Block BEETROOTS = getBlock(511);
    public static final Block DIRT_PATH = getBlock(512);
    public static final Block END_GATEWAY = getBlock(513);
    public static final Block REPEATING_COMMAND_BLOCK = getBlock(514);
    public static final Block CHAIN_COMMAND_BLOCK = getBlock(515);
    public static final Block FROSTED_ICE = getBlock(516);
    public static final Block MAGMA_BLOCK = getBlock(517);
    public static final Block NETHER_WART_BLOCK = getBlock(518);
    public static final Block RED_NETHER_BRICKS = getBlock(519);
    public static final Block BONE_BLOCK = getBlock(520);
    public static final Block STRUCTURE_VOID = getBlock(521);
    public static final Block OBSERVER = getBlock(522);
    public static final Block SHULKER_BOX = getBlock(523);
    public static final Block WHITE_SHULKER_BOX = getBlock(524);
    public static final Block ORANGE_SHULKER_BOX = getBlock(525);
    public static final Block MAGENTA_SHULKER_BOX = getBlock(526);
    public static final Block LIGHT_BLUE_SHULKER_BOX = getBlock(527);
    public static final Block YELLOW_SHULKER_BOX = getBlock(528);
    public static final Block LIME_SHULKER_BOX = getBlock(529);
    public static final Block PINK_SHULKER_BOX = getBlock(530);
    public static final Block GRAY_SHULKER_BOX = getBlock(531);
    public static final Block LIGHT_GRAY_SHULKER_BOX = getBlock(532);
    public static final Block CYAN_SHULKER_BOX = getBlock(533);
    public static final Block PURPLE_SHULKER_BOX = getBlock(534);
    public static final Block BLUE_SHULKER_BOX = getBlock(535);
    public static final Block BROWN_SHULKER_BOX = getBlock(536);
    public static final Block GREEN_SHULKER_BOX = getBlock(537);
    public static final Block RED_SHULKER_BOX = getBlock(538);
    public static final Block BLACK_SHULKER_BOX = getBlock(539);
    public static final Block WHITE_GLAZED_TERRACOTTA = getBlock(540);
    public static final Block ORANGE_GLAZED_TERRACOTTA = getBlock(541);
    public static final Block MAGENTA_GLAZED_TERRACOTTA = getBlock(542);
    public static final Block LIGHT_BLUE_GLAZED_TERRACOTTA = getBlock(543);
    public static final Block YELLOW_GLAZED_TERRACOTTA = getBlock(544);
    public static final Block LIME_GLAZED_TERRACOTTA = getBlock(545);
    public static final Block PINK_GLAZED_TERRACOTTA = getBlock(546);
    public static final Block GRAY_GLAZED_TERRACOTTA = getBlock(547);
    public static final Block LIGHT_GRAY_GLAZED_TERRACOTTA = getBlock(548);
    public static final Block CYAN_GLAZED_TERRACOTTA = getBlock(549);
    public static final Block PURPLE_GLAZED_TERRACOTTA = getBlock(550);
    public static final Block BLUE_GLAZED_TERRACOTTA = getBlock(551);
    public static final Block BROWN_GLAZED_TERRACOTTA = getBlock(552);
    public static final Block GREEN_GLAZED_TERRACOTTA = getBlock(553);
    public static final Block RED_GLAZED_TERRACOTTA = getBlock(554);
    public static final Block BLACK_GLAZED_TERRACOTTA = getBlock(555);
    public static final Block WHITE_CONCRETE = getBlock(556);
    public static final Block ORANGE_CONCRETE = getBlock(557);
    public static final Block MAGENTA_CONCRETE = getBlock(558);
    public static final Block LIGHT_BLUE_CONCRETE = getBlock(559);
    public static final Block YELLOW_CONCRETE = getBlock(560);
    public static final Block LIME_CONCRETE = getBlock(561);
    public static final Block PINK_CONCRETE = getBlock(562);
    public static final Block GRAY_CONCRETE = getBlock(563);
    public static final Block LIGHT_GRAY_CONCRETE = getBlock(564);
    public static final Block CYAN_CONCRETE = getBlock(565);
    public static final Block PURPLE_CONCRETE = getBlock(566);
    public static final Block BLUE_CONCRETE = getBlock(567);
    public static final Block BROWN_CONCRETE = getBlock(568);
    public static final Block GREEN_CONCRETE = getBlock(569);
    public static final Block RED_CONCRETE = getBlock(570);
    public static final Block BLACK_CONCRETE = getBlock(571);
    public static final Block WHITE_CONCRETE_POWDER = getBlock(572);
    public static final Block ORANGE_CONCRETE_POWDER = getBlock(573);
    public static final Block MAGENTA_CONCRETE_POWDER = getBlock(574);
    public static final Block LIGHT_BLUE_CONCRETE_POWDER = getBlock(575);
    public static final Block YELLOW_CONCRETE_POWDER = getBlock(576);
    public static final Block LIME_CONCRETE_POWDER = getBlock(577);
    public static final Block PINK_CONCRETE_POWDER = getBlock(578);
    public static final Block GRAY_CONCRETE_POWDER = getBlock(579);
    public static final Block LIGHT_GRAY_CONCRETE_POWDER = getBlock(580);
    public static final Block CYAN_CONCRETE_POWDER = getBlock(581);
    public static final Block PURPLE_CONCRETE_POWDER = getBlock(582);
    public static final Block BLUE_CONCRETE_POWDER = getBlock(583);
    public static final Block BROWN_CONCRETE_POWDER = getBlock(584);
    public static final Block GREEN_CONCRETE_POWDER = getBlock(585);
    public static final Block RED_CONCRETE_POWDER = getBlock(586);
    public static final Block BLACK_CONCRETE_POWDER = getBlock(587);
    public static final Block KELP = getBlock(588);
    public static final Block KELP_PLANT = getBlock(589);
    public static final Block DRIED_KELP_BLOCK = getBlock(590);
    public static final Block TURTLE_EGG = getBlock(591);
    public static final Block DEAD_TUBE_CORAL_BLOCK = getBlock(592);
    public static final Block DEAD_BRAIN_CORAL_BLOCK = getBlock(593);
    public static final Block DEAD_BUBBLE_CORAL_BLOCK = getBlock(594);
    public static final Block DEAD_FIRE_CORAL_BLOCK = getBlock(595);
    public static final Block DEAD_HORN_CORAL_BLOCK = getBlock(596);
    public static final Block TUBE_CORAL_BLOCK = getBlock(597);
    public static final Block BRAIN_CORAL_BLOCK = getBlock(598);
    public static final Block BUBBLE_CORAL_BLOCK = getBlock(599);
    public static final Block FIRE_CORAL_BLOCK = getBlock(600);
    public static final Block HORN_CORAL_BLOCK = getBlock(601);
    public static final Block DEAD_TUBE_CORAL = getBlock(602);
    public static final Block DEAD_BRAIN_CORAL = getBlock(603);
    public static final Block DEAD_BUBBLE_CORAL = getBlock(604);
    public static final Block DEAD_FIRE_CORAL = getBlock(605);
    public static final Block DEAD_HORN_CORAL = getBlock(606);
    public static final Block TUBE_CORAL = getBlock(607);
    public static final Block BRAIN_CORAL = getBlock(608);
    public static final Block BUBBLE_CORAL = getBlock(609);
    public static final Block FIRE_CORAL = getBlock(610);
    public static final Block HORN_CORAL = getBlock(611);
    public static final Block DEAD_TUBE_CORAL_FAN = getBlock(612);
    public static final Block DEAD_BRAIN_CORAL_FAN = getBlock(613);
    public static final Block DEAD_BUBBLE_CORAL_FAN = getBlock(614);
    public static final Block DEAD_FIRE_CORAL_FAN = getBlock(615);
    public static final Block DEAD_HORN_CORAL_FAN = getBlock(616);
    public static final Block TUBE_CORAL_FAN = getBlock(617);
    public static final Block BRAIN_CORAL_FAN = getBlock(618);
    public static final Block BUBBLE_CORAL_FAN = getBlock(619);
    public static final Block FIRE_CORAL_FAN = getBlock(620);
    public static final Block HORN_CORAL_FAN = getBlock(621);
    public static final Block DEAD_TUBE_CORAL_WALL_FAN = getBlock(622);
    public static final Block DEAD_BRAIN_CORAL_WALL_FAN = getBlock(623);
    public static final Block DEAD_BUBBLE_CORAL_WALL_FAN = getBlock(624);
    public static final Block DEAD_FIRE_CORAL_WALL_FAN = getBlock(625);
    public static final Block DEAD_HORN_CORAL_WALL_FAN = getBlock(626);
    public static final Block TUBE_CORAL_WALL_FAN = getBlock(627);
    public static final Block BRAIN_CORAL_WALL_FAN = getBlock(628);
    public static final Block BUBBLE_CORAL_WALL_FAN = getBlock(629);
    public static final Block FIRE_CORAL_WALL_FAN = getBlock(630);
    public static final Block HORN_CORAL_WALL_FAN = getBlock(631);
    public static final Block SEA_PICKLE = getBlock(632);
    public static final Block BLUE_ICE = getBlock(633);
    public static final Block CONDUIT = getBlock(634);
    public static final Block BAMBOO_SAPLING = getBlock(635);
    public static final Block BAMBOO = getBlock(636);
    public static final Block POTTED_BAMBOO = getBlock(637);
    public static final Block VOID_AIR = getBlock(638);
    public static final Block CAVE_AIR = getBlock(639);
    public static final Block BUBBLE_COLUMN = getBlock(640);
    public static final Block POLISHED_GRANITE_STAIRS = getBlock(641);
    public static final Block SMOOTH_RED_SANDSTONE_STAIRS = getBlock(642);
    public static final Block MOSSY_STONE_BRICK_STAIRS = getBlock(643);
    public static final Block POLISHED_DIORITE_STAIRS = getBlock(644);
    public static final Block MOSSY_COBBLESTONE_STAIRS = getBlock(645);
    public static final Block END_STONE_BRICK_STAIRS = getBlock(646);
    public static final Block STONE_STAIRS = getBlock(647);
    public static final Block SMOOTH_SANDSTONE_STAIRS = getBlock(648);
    public static final Block SMOOTH_QUARTZ_STAIRS = getBlock(649);
    public static final Block GRANITE_STAIRS = getBlock(650);
    public static final Block ANDESITE_STAIRS = getBlock(651);
    public static final Block RED_NETHER_BRICK_STAIRS = getBlock(652);
    public static final Block POLISHED_ANDESITE_STAIRS = getBlock(653);
    public static final Block DIORITE_STAIRS = getBlock(654);
    public static final Block POLISHED_GRANITE_SLAB = getBlock(655);
    public static final Block SMOOTH_RED_SANDSTONE_SLAB = getBlock(656);
    public static final Block MOSSY_STONE_BRICK_SLAB = getBlock(657);
    public static final Block POLISHED_DIORITE_SLAB = getBlock(658);
    public static final Block MOSSY_COBBLESTONE_SLAB = getBlock(659);
    public static final Block END_STONE_BRICK_SLAB = getBlock(660);
    public static final Block SMOOTH_SANDSTONE_SLAB = getBlock(661);
    public static final Block SMOOTH_QUARTZ_SLAB = getBlock(662);
    public static final Block GRANITE_SLAB = getBlock(663);
    public static final Block ANDESITE_SLAB = getBlock(664);
    public static final Block RED_NETHER_BRICK_SLAB = getBlock(665);
    public static final Block POLISHED_ANDESITE_SLAB = getBlock(666);
    public static final Block DIORITE_SLAB = getBlock(667);
    public static final Block BRICK_WALL = getBlock(668);
    public static final Block PRISMARINE_WALL = getBlock(669);
    public static final Block RED_SANDSTONE_WALL = getBlock(670);
    public static final Block MOSSY_STONE_BRICK_WALL = getBlock(671);
    public static final Block GRANITE_WALL = getBlock(672);
    public static final Block STONE_BRICK_WALL = getBlock(673);
    public static final Block NETHER_BRICK_WALL = getBlock(674);
    public static final Block ANDESITE_WALL = getBlock(675);
    public static final Block RED_NETHER_BRICK_WALL = getBlock(676);
    public static final Block SANDSTONE_WALL = getBlock(677);
    public static final Block END_STONE_BRICK_WALL = getBlock(678);
    public static final Block DIORITE_WALL = getBlock(679);
    public static final Block SCAFFOLDING = getBlock(680);
    public static final Block LOOM = getBlock(681);
    public static final Block BARREL = getBlock(682);
    public static final Block SMOKER = getBlock(683);
    public static final Block BLAST_FURNACE = getBlock(684);
    public static final Block CARTOGRAPHY_TABLE = getBlock(685);
    public static final Block FLETCHING_TABLE = getBlock(686);
    public static final Block GRINDSTONE = getBlock(687);
    public static final Block LECTERN = getBlock(688);
    public static final Block SMITHING_TABLE = getBlock(689);
    public static final Block STONECUTTER = getBlock(690);
    public static final Block BELL = getBlock(691);
    public static final Block LANTERN = getBlock(692);
    public static final Block SOUL_LANTERN = getBlock(693);
    public static final Block CAMPFIRE = getBlock(694);
    public static final Block SOUL_CAMPFIRE = getBlock(695);
    public static final Block SWEET_BERRY_BUSH = getBlock(696);
    public static final Block WARPED_STEM = getBlock(697);
    public static final Block STRIPPED_WARPED_STEM = getBlock(698);
    public static final Block WARPED_HYPHAE = getBlock(699);
    public static final Block STRIPPED_WARPED_HYPHAE = getBlock(700);
    public static final Block WARPED_NYLIUM = getBlock(701);
    public static final Block WARPED_FUNGUS = getBlock(702);
    public static final Block WARPED_WART_BLOCK = getBlock(703);
    public static final Block WARPED_ROOTS = getBlock(704);
    public static final Block NETHER_SPROUTS = getBlock(705);
    public static final Block CRIMSON_STEM = getBlock(706);
    public static final Block STRIPPED_CRIMSON_STEM = getBlock(707);
    public static final Block CRIMSON_HYPHAE = getBlock(708);
    public static final Block STRIPPED_CRIMSON_HYPHAE = getBlock(709);
    public static final Block CRIMSON_NYLIUM = getBlock(710);
    public static final Block CRIMSON_FUNGUS = getBlock(711);
    public static final Block SHROOMLIGHT = getBlock(712);
    public static final Block WEEPING_VINES = getBlock(713);
    public static final Block WEEPING_VINES_PLANT = getBlock(714);
    public static final Block TWISTING_VINES = getBlock(715);
    public static final Block TWISTING_VINES_PLANT = getBlock(716);
    public static final Block CRIMSON_ROOTS = getBlock(717);
    public static final Block CRIMSON_PLANKS = getBlock(718);
    public static final Block WARPED_PLANKS = getBlock(719);
    public static final Block CRIMSON_SLAB = getBlock(720);
    public static final Block WARPED_SLAB = getBlock(721);
    public static final Block CRIMSON_PRESSURE_PLATE = getBlock(722);
    public static final Block WARPED_PRESSURE_PLATE = getBlock(723);
    public static final Block CRIMSON_FENCE = getBlock(724);
    public static final Block WARPED_FENCE = getBlock(725);
    public static final Block CRIMSON_TRAPDOOR = getBlock(726);
    public static final Block WARPED_TRAPDOOR = getBlock(727);
    public static final Block CRIMSON_FENCE_GATE = getBlock(728);
    public static final Block WARPED_FENCE_GATE = getBlock(729);
    public static final Block CRIMSON_STAIRS = getBlock(730);
    public static final Block WARPED_STAIRS = getBlock(731);
    public static final Block CRIMSON_BUTTON = getBlock(732);
    public static final Block WARPED_BUTTON = getBlock(733);
    public static final Block CRIMSON_DOOR = getBlock(734);
    public static final Block WARPED_DOOR = getBlock(735);
    public static final Block CRIMSON_SIGN = getBlock(736);
    public static final Block WARPED_SIGN = getBlock(737);
    public static final Block CRIMSON_WALL_SIGN = getBlock(738);
    public static final Block WARPED_WALL_SIGN = getBlock(739);
    public static final Block STRUCTURE_BLOCK = getBlock(740);
    public static final Block JIGSAW = getBlock(741);
    public static final Block COMPOSTER = getBlock(742);
    public static final Block TARGET = getBlock(743);
    public static final Block BEE_NEST = getBlock(744);
    public static final Block BEEHIVE = getBlock(745);
    public static final Block HONEY_BLOCK = getBlock(746);
    public static final Block HONEYCOMB_BLOCK = getBlock(747);
    public static final Block NETHERITE_BLOCK = getBlock(748);
    public static final Block ANCIENT_DEBRIS = getBlock(749);
    public static final Block CRYING_OBSIDIAN = getBlock(750);
    public static final Block RESPAWN_ANCHOR = getBlock(751);
    public static final Block POTTED_CRIMSON_FUNGUS = getBlock(752);
    public static final Block POTTED_WARPED_FUNGUS = getBlock(753);
    public static final Block POTTED_CRIMSON_ROOTS = getBlock(754);
    public static final Block POTTED_WARPED_ROOTS = getBlock(755);
    public static final Block LODESTONE = getBlock(756);
    public static final Block BLACKSTONE = getBlock(757);
    public static final Block BLACKSTONE_STAIRS = getBlock(758);
    public static final Block BLACKSTONE_WALL = getBlock(759);
    public static final Block BLACKSTONE_SLAB = getBlock(760);
    public static final Block POLISHED_BLACKSTONE = getBlock(761);
    public static final Block POLISHED_BLACKSTONE_BRICKS = getBlock(762);
    public static final Block CRACKED_POLISHED_BLACKSTONE_BRICKS = getBlock(763);
    public static final Block CHISELED_POLISHED_BLACKSTONE = getBlock(764);
    public static final Block POLISHED_BLACKSTONE_BRICK_SLAB = getBlock(765);
    public static final Block POLISHED_BLACKSTONE_BRICK_STAIRS = getBlock(766);
    public static final Block POLISHED_BLACKSTONE_BRICK_WALL = getBlock(767);
    public static final Block GILDED_BLACKSTONE = getBlock(768);
    public static final Block POLISHED_BLACKSTONE_STAIRS = getBlock(769);
    public static final Block POLISHED_BLACKSTONE_SLAB = getBlock(770);
    public static final Block POLISHED_BLACKSTONE_PRESSURE_PLATE = getBlock(771);
    public static final Block POLISHED_BLACKSTONE_BUTTON = getBlock(772);
    public static final Block POLISHED_BLACKSTONE_WALL = getBlock(773);
    public static final Block CHISELED_NETHER_BRICKS = getBlock(774);
    public static final Block CRACKED_NETHER_BRICKS = getBlock(775);
    public static final Block QUARTZ_BRICKS = getBlock(776);
    public static final Block CANDLE = getBlock(777);
    public static final Block WHITE_CANDLE = getBlock(778);
    public static final Block ORANGE_CANDLE = getBlock(779);
    public static final Block MAGENTA_CANDLE = getBlock(780);
    public static final Block LIGHT_BLUE_CANDLE = getBlock(781);
    public static final Block YELLOW_CANDLE = getBlock(782);
    public static final Block LIME_CANDLE = getBlock(783);
    public static final Block PINK_CANDLE = getBlock(784);
    public static final Block GRAY_CANDLE = getBlock(785);
    public static final Block LIGHT_GRAY_CANDLE = getBlock(786);
    public static final Block CYAN_CANDLE = getBlock(787);
    public static final Block PURPLE_CANDLE = getBlock(788);
    public static final Block BLUE_CANDLE = getBlock(789);
    public static final Block BROWN_CANDLE = getBlock(790);
    public static final Block GREEN_CANDLE = getBlock(791);
    public static final Block RED_CANDLE = getBlock(792);
    public static final Block BLACK_CANDLE = getBlock(793);
    public static final Block CANDLE_CAKE = getBlock(794);
    public static final Block WHITE_CANDLE_CAKE = getBlock(795);
    public static final Block ORANGE_CANDLE_CAKE = getBlock(796);
    public static final Block MAGENTA_CANDLE_CAKE = getBlock(797);
    public static final Block LIGHT_BLUE_CANDLE_CAKE = getBlock(798);
    public static final Block YELLOW_CANDLE_CAKE = getBlock(799);
    public static final Block LIME_CANDLE_CAKE = getBlock(800);
    public static final Block PINK_CANDLE_CAKE = getBlock(801);
    public static final Block GRAY_CANDLE_CAKE = getBlock(802);
    public static final Block LIGHT_GRAY_CANDLE_CAKE = getBlock(803);
    public static final Block CYAN_CANDLE_CAKE = getBlock(804);
    public static final Block PURPLE_CANDLE_CAKE = getBlock(805);
    public static final Block BLUE_CANDLE_CAKE = getBlock(806);
    public static final Block BROWN_CANDLE_CAKE = getBlock(807);
    public static final Block GREEN_CANDLE_CAKE = getBlock(808);
    public static final Block RED_CANDLE_CAKE = getBlock(809);
    public static final Block BLACK_CANDLE_CAKE = getBlock(810);
    public static final Block AMETHYST_BLOCK = getBlock(811);
    public static final Block BUDDING_AMETHYST = getBlock(812);
    public static final Block AMETHYST_CLUSTER = getBlock(813);
    public static final Block LARGE_AMETHYST_BUD = getBlock(814);
    public static final Block MEDIUM_AMETHYST_BUD = getBlock(815);
    public static final Block SMALL_AMETHYST_BUD = getBlock(816);
    public static final Block TUFF = getBlock(817);
    public static final Block CALCITE = getBlock(818);
    public static final Block TINTED_GLASS = getBlock(819);
    public static final Block POWDER_SNOW = getBlock(820);
    public static final Block SCULK_SENSOR = getBlock(821);
    public static final Block OXIDIZED_COPPER = getBlock(822);
    public static final Block WEATHERED_COPPER = getBlock(823);
    public static final Block EXPOSED_COPPER = getBlock(824);
    public static final Block COPPER_BLOCK = getBlock(825);
    public static final Block COPPER_ORE = getBlock(826);
    public static final Block DEEPSLATE_COPPER_ORE = getBlock(827);
    public static final Block OXIDIZED_CUT_COPPER = getBlock(828);
    public static final Block WEATHERED_CUT_COPPER = getBlock(829);
    public static final Block EXPOSED_CUT_COPPER = getBlock(830);
    public static final Block CUT_COPPER = getBlock(831);
    public static final Block OXIDIZED_CUT_COPPER_STAIRS = getBlock(832);
    public static final Block WEATHERED_CUT_COPPER_STAIRS = getBlock(833);
    public static final Block EXPOSED_CUT_COPPER_STAIRS = getBlock(834);
    public static final Block CUT_COPPER_STAIRS = getBlock(835);
    public static final Block OXIDIZED_CUT_COPPER_SLAB = getBlock(836);
    public static final Block WEATHERED_CUT_COPPER_SLAB = getBlock(837);
    public static final Block EXPOSED_CUT_COPPER_SLAB = getBlock(838);
    public static final Block CUT_COPPER_SLAB = getBlock(839);
    public static final Block WAXED_COPPER_BLOCK = getBlock(840);
    public static final Block WAXED_WEATHERED_COPPER = getBlock(841);
    public static final Block WAXED_EXPOSED_COPPER = getBlock(842);
    public static final Block WAXED_OXIDIZED_COPPER = getBlock(843);
    public static final Block WAXED_OXIDIZED_CUT_COPPER = getBlock(844);
    public static final Block WAXED_WEATHERED_CUT_COPPER = getBlock(845);
    public static final Block WAXED_EXPOSED_CUT_COPPER = getBlock(846);
    public static final Block WAXED_CUT_COPPER = getBlock(847);
    public static final Block WAXED_OXIDIZED_CUT_COPPER_STAIRS = getBlock(848);
    public static final Block WAXED_WEATHERED_CUT_COPPER_STAIRS = getBlock(849);
    public static final Block WAXED_EXPOSED_CUT_COPPER_STAIRS = getBlock(850);
    public static final Block WAXED_CUT_COPPER_STAIRS = getBlock(851);
    public static final Block WAXED_OXIDIZED_CUT_COPPER_SLAB = getBlock(852);
    public static final Block WAXED_WEATHERED_CUT_COPPER_SLAB = getBlock(853);
    public static final Block WAXED_EXPOSED_CUT_COPPER_SLAB = getBlock(854);
    public static final Block WAXED_CUT_COPPER_SLAB = getBlock(855);
    public static final Block LIGHTNING_ROD = getBlock(856);
    public static final Block POINTED_DRIPSTONE = getBlock(857);
    public static final Block DRIPSTONE_BLOCK = getBlock(858);
    public static final Block CAVE_VINES = getBlock(859);
    public static final Block CAVE_VINES_PLANT = getBlock(860);
    public static final Block SPORE_BLOSSOM = getBlock(861);
    public static final Block AZALEA = getBlock(862);
    public static final Block FLOWERING_AZALEA = getBlock(863);
    public static final Block MOSS_CARPET = getBlock(864);
    public static final Block MOSS_BLOCK = getBlock(865);
    public static final Block BIG_DRIPLEAF = getBlock(866);
    public static final Block BIG_DRIPLEAF_STEM = getBlock(867);
    public static final Block SMALL_DRIPLEAF = getBlock(868);
    public static final Block HANGING_ROOTS = getBlock(869);
    public static final Block ROOTED_DIRT = getBlock(870);
    public static final Block DEEPSLATE = getBlock(871);
    public static final Block COBBLED_DEEPSLATE = getBlock(872);
    public static final Block COBBLED_DEEPSLATE_STAIRS = getBlock(873);
    public static final Block COBBLED_DEEPSLATE_SLAB = getBlock(874);
    public static final Block COBBLED_DEEPSLATE_WALL = getBlock(875);
    public static final Block POLISHED_DEEPSLATE = getBlock(876);
    public static final Block POLISHED_DEEPSLATE_STAIRS = getBlock(877);
    public static final Block POLISHED_DEEPSLATE_SLAB = getBlock(878);
    public static final Block POLISHED_DEEPSLATE_WALL = getBlock(879);
    public static final Block DEEPSLATE_TILES = getBlock(880);
    public static final Block DEEPSLATE_TILE_STAIRS = getBlock(881);
    public static final Block DEEPSLATE_TILE_SLAB = getBlock(882);
    public static final Block DEEPSLATE_TILE_WALL = getBlock(883);
    public static final Block DEEPSLATE_BRICKS = getBlock(884);
    public static final Block DEEPSLATE_BRICK_STAIRS = getBlock(885);
    public static final Block DEEPSLATE_BRICK_SLAB = getBlock(886);
    public static final Block DEEPSLATE_BRICK_WALL = getBlock(887);
    public static final Block CHISELED_DEEPSLATE = getBlock(888);
    public static final Block CRACKED_DEEPSLATE_BRICKS = getBlock(889);
    public static final Block CRACKED_DEEPSLATE_TILES = getBlock(890);
    public static final Block INFESTED_DEEPSLATE = getBlock(891);
    public static final Block SMOOTH_BASALT = getBlock(892);
    public static final Block RAW_IRON_BLOCK = getBlock(893);
    public static final Block RAW_COPPER_BLOCK = getBlock(894);
    public static final Block RAW_GOLD_BLOCK = getBlock(895);
    public static final Block POTTED_AZALEA_BUSH = getBlock(896);
    public static final Block POTTED_FLOWERING_AZALEA_BUSH = getBlock(897);

    // End auto generated code

    /**
     * Gets a block by its name
     * @param name Block Name
     * @return Block
     */
    public static Block getBlock(String name) {
        return REGISTRY.stream().filter(block -> block.getName().equalsIgnoreCase(name)).collect(toSingleton());
    }

    /**
     * Gets a block by its ID
     * @param id Block ID
     * @return Block
     */
    public static Block getBlock(int id) {
        return REGISTRY.stream().filter(block -> block.getId() == id).collect(toSingleton());
    }

    /**
     * Gets a Block from the provided state
     * @param state Block State
     * @return Block
     */
    public static Block getBlockFromState(long state) {
        return REGISTRY.stream()
                .filter(block -> block.getDefaultState() == state || (block.getMinStateId() < state && block.getMaxStateId() > state))
                .collect(toSingleton());
    }

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() == 0) throw new NullPointerException();
                    else if (list.size() > 1) throw new IllegalStateException();
                    else return list.get(0);
                }
        );
    }

    private final int id;
    private final String displayName;
    private final String name;
    private final double hardness;
    private final double resistance;
    private final long minStateId;
    private final long maxStateId;
    private final long defaultState;
    // TODO: States Array
    private final boolean diggable;
    private final int emitLight;
    private final String material;
    private final int[] harvestTools;


    private Block(
            int id,
            String displayName,
            String name,
            double hardness,
            double resistance,
            long minStateId,
            long maxStateId,
            long defaultState,
            boolean diggable,
            int emitLight,
            String material,
            int[] harvestTools
    ) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
        this.hardness = hardness;
        this.resistance = resistance;
        this.minStateId = minStateId;
        this.maxStateId = maxStateId;
        this.defaultState = defaultState;
        this.diggable = diggable;
        this.emitLight = emitLight;
        this.material = material;
        this.harvestTools = harvestTools;
    }

    private Block(
            int id,
            String displayName,
            String name,
            double hardness,
            double resistance,
            long minStateId,
            long maxStateId,
            long defaultState,
            boolean diggable,
            int emitLight,
            String material
    ) {
        this(
                id,
                displayName,
                name,
                hardness,
                resistance,
                minStateId,
                maxStateId,
                defaultState,
                diggable,
                emitLight,
                material,
                new int[0]
        );
    }
}