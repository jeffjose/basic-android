from pine.compiler.parser import expand_component_line
from pytest_cases import parametrize_with_cases


def case_external_var_with_type():

    inp = "external var first: Int"

    out = (
        "var first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


def case_external_var_with_type_nospaces():

    inp = "external var first:Int"

    out = (
        "var first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


def case_external_var_with_type_lots_of_spaces():

    inp = "external    var   first    :   Int"

    out = (
        "var first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


#####################


def case_external_val_with_type():

    inp = "external val first: Int"

    out = (
        "val first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


def case_external_val_with_type_nospaces():

    inp = "external val first:Int"

    out = (
        "val first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


def case_external_val_with_type_lots_of_spaces():

    inp = "external    val   first    :   Int"

    out = (
        "val first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


#####################


def case_external_var_with_type_with_default_value():

    inp = "external var first: Int = 10"

    out = (
        "var first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


def case_external_var_with_type_nospaces_with_default_value():

    inp = "external var first:Int=10"

    out = (
        "var first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


def case_external_var_with_type_lots_of_spaces_with_default_value():

    inp = "external    var   first    :   Int           =       10"

    out = (
        "var first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


#####################


def case_external_val_with_type_with_default_value():

    inp = "external val first: Int = 10"

    out = (
        "val first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


def case_external_val_with_type_nospaces_with_default_value():

    inp = "external val first:Int=10"

    out = (
        "val first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


def case_external_val_with_type_lots_of_spaces_with_default_value():

    inp = "external    val   first    :   Int    =          10"

    out = (
        "val first by rememberSaveable(inputs=arrayOf(first)) { mutableStateOf(first) }"
    )

    return inp, out


@parametrize_with_cases("line,expected", cases=".")
def test_expand_component_line(line, expected):

    print(line)
    print(expected)
    assert expand_component_line(line, [], []) == expected
