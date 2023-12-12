use std::collections::{HashMap, LinkedList};
use crate::aoc2023::day5::correlation_map;

use crate::aoc2023::day5::correlation_map::CorrelationMap;
use crate::prelude::*;

pub struct IfYouGiveASeedAFertilizer;

impl solution::Solution for IfYouGiveASeedAFertilizer {
    fn solve_part_one(&self, input_content: &String) -> String {
        let (seeds_line, map_lines) = input_content.split_once("\n\n").expect("Should have seeds line");

        let seeds = seeds_line
            .split_once("seeds: ")
            .expect("First line should be the seeds mapping")
            .1
            .split(" ")
            .map(|seed| seed.parse::<u64>().expect("Should be a valid number"))
            .collect::<LinkedList<u64>>();

        let correlation_maps: HashMap<&str, CorrelationMap> = setup_correlation_maps(map_lines);

        seeds
            .iter()
            .map(|seed| map_to_location(*seed, &correlation_maps))
            .min()
            .expect("Should have at least one location")
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().map(&str::len).max().unwrap().to_string()
    }
}

fn setup_correlation_maps(map_lines: &str) -> HashMap<&str, CorrelationMap> {
    map_lines.split("\n\n")
        .map(parse_correlation_map)
        .collect()
}

fn parse_correlation_map(map_lines: &str) -> (&str, CorrelationMap) {
    let (description, mapping_lines) = map_lines
        .split_once(" map:\n")
        .expect("Should have header line");

    let correlation_map = correlation_map::from_lines(mapping_lines);

    (description, correlation_map)
}

fn map_to_location(seed: u64, correlation_maps: &HashMap<&str, CorrelationMap>) -> u64 {
    let mapping = correlate_source(seed, "seed-to-soil", correlation_maps);
    let mapping = correlate_source(mapping, "soil-to-fertilizer", correlation_maps);
    let mapping = correlate_source(mapping, "fertilizer-to-water", correlation_maps);
    let mapping = correlate_source(mapping, "water-to-light", correlation_maps);
    let mapping = correlate_source(mapping, "light-to-temperature", correlation_maps);
    let mapping = correlate_source(mapping, "temperature-to-humidity", correlation_maps);
    correlate_source(mapping, "humidity-to-location", correlation_maps)
}

fn correlate_source(source: u64, map_desc: &str, correlation_maps: &HashMap<&str, CorrelationMap>) -> u64 {
    let mapping = correlation_maps
        .get(map_desc)
        .expect("Should have map")
        .correlate(source);
    mapping
}
