use crate::aoc2023::day5::mapping;
use crate::aoc2023::day5::mapping::Mapping;

pub struct CorrelationMap {
    mappings: Vec<Mapping>
}

impl CorrelationMap {
    pub fn correlate(&self, source: u64) -> u64 {
        let destination = self.mappings.iter()
            .filter_map(|mapping| mapping.correlate(source))
            .nth(0);

        match destination {
            None => source,
            Some(dest) => dest
        }
    }
}

pub fn from_lines(mapping_lines: &str) -> CorrelationMap {
    let mappings: Vec<Mapping> = mapping_lines.lines()
        .map(mapping::from_line)
        .collect();

    CorrelationMap {
        mappings
    }
}
