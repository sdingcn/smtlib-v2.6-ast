# smtlib-v2.6-ast

The abstract syntax tree for the SMT-LIB v2.6 language.

This is self-contained; you only need ```java``` (>= 8) and ```python``` (>= 3.6). It works at least on Ubuntu.

Running ```python3 executor.py``` will produce the usage message.

## example

The following commands build the project, read ```formula-file.smt2```'s content, and pretty-print the content to ```stdout```.

```python3 executor.py build```

```cat formula-file.smt2 | python3 executor.py run pretty-print```

## unsupported features

Certain SMT-LIB v2.6 features are currently not supported in the AST generator: check-sat-assuming, datatypes,  recursive function definitions, get-value, pattern matching, etc. Based on my previous experience, most formulas in the SMT-LIB benchmarks are not using those features. If you apply the AST generator to a file containing unsupported features, it will generate an error.
