# scanner-parser
Compiler: Scanner + Parser using Jflex and BYacc respectively.

## To run

We support two ways of running scripts to generate the C file

1. **Generate the corresponding file in C**:
`./scripts/transpile.sh <file_name>`

2. **Generate file, compile and execute:**
`./scripts/execute.sh <file_name>`

When we say `<file_name>`, it's really the file name, without its extesion.


## Try it
Try `./scripts/transpile.sh exemplo_ct`
And then `./scripts/execute.sh exemplo_ct`



## Observations

To indent the generated C file, we recommended install `clang-format`
`sudo apt-get install clang-format`
And execute with
`clang-format -i <file_path>`
