import os
import os.path
import subprocess
import sys

def run_and_get_output(command, inputstr = None, tout = None, d = None):
    return subprocess.run(command,
            input = inputstr,
            stdout = subprocess.PIPE,
            stderr = subprocess.STDOUT,
            universal_newlines = True,
            timeout = tout,
            cwd = d).stdout

def build():
    if not os.path.exists('build'):
        os.mkdir('build')
    cwd = os.getcwd()
    antlr_command_list = ['java',
                          '-Xmx500M',
                          '-cp',
                          os.path.join(cwd, 'antlr4/antlr-4.8-complete.jar'),
                          'org.antlr.v4.Tool',
                          '-o',
                          'src/io/github/shuoding/',
                          '-visitor',
                          '-package',
                          'io.github.shuoding.grammar',
                          'grammar/SMTLIBv2.g4']
    javac_command_list = ['javac',
                          '-d',
                          'build',
                          '-cp',
                          os.path.join(cwd, 'src') + ':' + os.path.join(cwd, 'antlr4/antlr-4.8-complete.jar'),
                          'src/io/github/shuoding/main/Main.java']
    # first call antlr to generate the parser, which will be placed under src/io/github/shuoding/grammar/
    antlr_output = run_and_get_output(antlr_command_list)
    if antlr_output != '':
        sys.exit(antlr_output)
    # then call javac to compile the entire project
    javac_output = run_and_get_output(javac_command_list)
    if javac_output != '':
        sys.exit(javac_output)

def run(options):
    java_command_list = ['java',
                         '-Xss2M',
                         '-cp',
                         os.path.join(os.getcwd(), 'build') + ':' + os.path.join(os.getcwd(), 'antlr4/antlr-4.8-complete.jar'),
                         'io.github.shuoding.main.Main']
    return run_and_get_output(java_command_list + options, sys.stdin.read())

if __name__ == '__main__':
    if len(sys.argv) < 2:
        sys.exit('Usage:\n\tpython3 {} build\n\tpython3 {} run [option]...'.format(sys.argv[0], sys.argv[0]))
    if sys.argv[1] == 'build':
        build()
    elif sys.argv[1] == 'run':
        print(run(sys.argv[2:]), end = '')
