import os

def get_input(reference: object) -> str:
    return get_input_path(reference)

def get_input_path(reference: object, label: str = "") -> str:
    module = reference.__module__
    path_items = module.split(".")[1:-1]
    path_items.append(f"aoc-{path_items[-1]}{label}-input.txt")
    return os.path.join(*path_items)
