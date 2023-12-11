use std::collections::{HashMap, LinkedList};

use crate::aoc2023::day5::correlation_map::CorrelationMap;
use crate::prelude::*;

pub struct IfYouGiveASeedAFertilizer;

impl solution::Solution for IfYouGiveASeedAFertilizer {
    fn solve_part_one(&self, input_content: &String) -> String {
        let lines = input_content.lines().collect::<Vec<&str>>();
        let seeds = lines[0usize]
            .split_once("seeds: ")
            .expect("First line should be the seeds mapping")
            .1
            .split(" ")
            .map(|seed| seed.parse::<u32>().expect("Should be a valid number"))
            .collect::<LinkedList<u32>>();

        let correlation_maps: HashMap<&str, CorrelationMap> = setup_correlation_maps(lines);

        seeds
            .map(|seed| map_to_location(seed, correlation_maps))
            .min::<u32>()
            .expect("Should have at least one location")
            .to_string()
    }

    fn solve_part_two(&self, input_content: &String) -> String {
        input_content.lines().map(&str::len).max().unwrap().to_string()
    }
}

fn setup_correlation_maps(lines: Vec<&str>) -> HashMap<&str, CorrelationMap> {
    let mut index = 2usize; // Ignore seeds lines
    let mut mappings;
    todo!();
    mappings
}

fn map_to_location(seed: u32, correlation_maps: HashMap<&str, CorrelationMap>) -> u32 {
    let mapping = seed;
    let mapping = correlation_maps
        .get("seed-to-soil")
        .expect("Should have map")
        .correlate(seed);
    todo!();
    mapping
}
