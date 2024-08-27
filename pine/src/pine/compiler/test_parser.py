from parser import expand_component_line
from pytest_cases import parametrize_with_cases

@parametrize_with_cases('line,expected')
def  test_expand_component_line(line, expected):

    assert expand_component_line(line) == expected
