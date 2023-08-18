defmodule CalorieCountingTest do
  use ExUnit.Case

  @file_path __ENV__.file

  test "solve part one" do
    answer =
      @file_path
      |> AdventOfCodeUtils.get_input_file_lines()
      |> CalorieCounting.solve_part_one()

    assert answer == 71300
  end

  test "solve part one sample" do
    answer =
      @file_path
      |> AdventOfCodeUtils.get_input_file_lines("-sample")
      |> CalorieCounting.solve_part_one()

    assert answer == 24000
  end

  test "solve part two" do
    answer =
      @file_path
      |> AdventOfCodeUtils.get_input_file_lines()
      |> CalorieCounting.solve_part_two()

      assert answer == 209691
  end

  test "solve part two sample" do
    answer =
      @file_path
      |> AdventOfCodeUtils.get_input_file_lines("-sample")
      |> CalorieCounting.solve_part_two()

      assert answer == 45000
  end

  test "solve for a single item", do:
    assert CalorieCounting.solve_part_one(["1000"]) == 1000

  test "solve for multiple elfs with single item", do:
    assert CalorieCounting.solve_part_one(["1000", "", "2000"]) == 2000
end
