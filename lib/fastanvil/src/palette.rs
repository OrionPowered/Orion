use crate::palette::PaletteType::Singleton;

struct PalettedContainer {
    bits_per_entry: i32,
    data: Vec<i64>,
    palette: Palette,
}

enum ContainerType {
    BlockStates,
    BIOMES
}

enum PaletteType {
    Singleton,
    Indirect,
    Direct,
}

pub struct Palette {
    kind: PaletteType,
    data: Vec<i32>,
}

impl Palette {
    pub fn new(data: Vec<i32>, kind: PaletteType) -> Self {
        Self { kind, data }
    }
}

impl PalettedContainer {
    fn from(kind: ContainerType, bits_per_entry: i32, palette: Vec<i32>, data: Vec<i64>) -> Self {
        return match kind {
            ContainerType::BlockStates => {
                match bits_per_entry {
                    0 => PalettedContainer::new(bits_per_entry, data, Palette::new(palette.to_owned(), PaletteType::Singleton)),
                    1 | 2 | 3 | 4 => PalettedContainer::new(4, data, Palette::new(palette.to_owned(), PaletteType::Singleton)),
                    5 | 6 | 7 | 8 => PalettedContainer::new(bits_per_entry, data, Palette::new(palette.to_owned(), PaletteType::Indirect)),
                    _ => PalettedContainer::new(bits_per_entry, data, Palette::new(palette.to_owned(), PaletteType::Direct)),
                }
            }
            ContainerType::BIOMES => {
                match bits_per_entry {
                    0 => PalettedContainer::new(bits_per_entry, data, Palette::new(palette.to_owned(), PaletteType::Singleton)),
                    1 | 2 | 3 => PalettedContainer::new(bits_per_entry, data, Palette::new(palette.to_owned(), PaletteType::Indirect)),
                    _ => PalettedContainer::new(6, data, Palette::new(palette.to_owned(), PaletteType::Direct)),
                }
            }
        }
    }

    fn new(bits_per_entry: i32, data: Vec<i64>, palette: Palette) -> Self {
        Self { bits_per_entry, data, palette }
    }
}
