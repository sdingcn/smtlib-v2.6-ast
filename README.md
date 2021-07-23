# smtlib-v2.6-ast

This is self-contained; you only need ```java``` (>= 8) and ```python``` (>= 3.6). It works at least on Ubuntu.

Running ```python3 executor.py``` will produce the usage message.

## example

The following commands read ```formula-file.smt2```'s content and pretty-print it to ```stdout```.

```python3 executor.py build```

```cat formula-file.smt2 | python3 executor.py run pretty-print```
