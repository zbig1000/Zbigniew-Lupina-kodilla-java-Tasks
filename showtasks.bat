call runcrud.bat
if "%ERRORLEVEL%" == "0" goto open
echo.
echo COMPILATION ERROR - breaking work
goto fail

:open
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.
