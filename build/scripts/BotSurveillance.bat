@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  BotSurveillance startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and BOT_SURVEILLANCE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\BotSurveillance-1.0-SNAPSHOT.jar;%APP_HOME%\lib\JDA-3.6.0_354-withDependencies.jar;%APP_HOME%\lib\gson-2.7.jar;%APP_HOME%\lib\hibernate-redis-2.3.1.jar;%APP_HOME%\lib\redisson-2.5.0.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.7.6.jar;%APP_HOME%\lib\snakeyaml-1.15.jar;%APP_HOME%\lib\slf4j-simple-1.6.1.jar;%APP_HOME%\lib\hibernate-hikaricp-4.3.8.Final.jar;%APP_HOME%\lib\HikariCP-2.7.8.jar;%APP_HOME%\lib\mariadb-java-client-1.1.7.jar;%APP_HOME%\lib\hibernate-core-5.2.17.Final.jar;%APP_HOME%\lib\jsoup-1.11.3.jar;%APP_HOME%\lib\reactor-stream-2.0.8.RELEASE.jar;%APP_HOME%\lib\reactor-core-2.0.8.RELEASE.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\jna-3.3.0.jar;%APP_HOME%\lib\jna-3.3.0-platform.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.0.1.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.1.Final.jar;%APP_HOME%\lib\hibernate-jpa-2.1-api-1.0.0.Final.jar;%APP_HOME%\lib\fst-2.48.jar;%APP_HOME%\lib\javassist-3.22.0-GA.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\jboss-transaction-api_1.2_spec-1.0.1.Final.jar;%APP_HOME%\lib\jandex-2.0.3.Final.jar;%APP_HOME%\lib\classmate-1.3.0.jar;%APP_HOME%\lib\dom4j-1.6.1.jar;%APP_HOME%\lib\snappy-java-1.1.2.6.jar;%APP_HOME%\lib\jboss-logging-annotations-1.2.0.Beta1.jar;%APP_HOME%\lib\netty-handler-4.0.42.Final.jar;%APP_HOME%\lib\netty-codec-4.0.42.Final.jar;%APP_HOME%\lib\netty-transport-4.0.42.Final.jar;%APP_HOME%\lib\netty-buffer-4.0.42.Final.jar;%APP_HOME%\lib\netty-common-4.0.42.Final.jar;%APP_HOME%\lib\jackson-databind-2.7.6.jar;%APP_HOME%\lib\jackson-core-2.8.1.jar;%APP_HOME%\lib\zero-allocation-hashing-0.5.jar;%APP_HOME%\lib\byte-buddy-1.4.26.jar;%APP_HOME%\lib\jodd-bean-3.7.1.jar;%APP_HOME%\lib\objenesis-2.4.jar;%APP_HOME%\lib\java-util-1.9.0.jar;%APP_HOME%\lib\jackson-annotations-2.7.0.jar;%APP_HOME%\lib\jodd-core-3.7.1.jar;%APP_HOME%\lib\commons-logging-1.1.1.jar;%APP_HOME%\lib\json-io-2.5.1.jar;%APP_HOME%\lib\reactive-streams-1.0.0.jar

@rem Execute BotSurveillance
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %BOT_SURVEILLANCE_OPTS%  -classpath "%CLASSPATH%" fr.fanaticstudio.matthis974jump.botsurveillancediscord.MainSystem %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable BOT_SURVEILLANCE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%BOT_SURVEILLANCE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
