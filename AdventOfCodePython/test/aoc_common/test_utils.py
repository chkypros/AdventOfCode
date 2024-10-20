import os
from pathlib import Path


def get_input(reference: object) -> str:
    return get_input_path(reference).read_text()

def get_input_path(reference: object, label: str = "") -> Path:
    module = reference.__module__
    sub_path = module.split(".")[1:-1]
    sub_path.append(f"aoc-{sub_path[-1]}{label}-input.txt")

    return Path(__file__).parents[3].joinpath('inputs', *sub_path)
