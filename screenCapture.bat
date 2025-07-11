hdc_std shell mount -o remount,rw /
set filepath=/data/%date:~0,4%%date:~5,2%%date:~8,2%%time:~1,1%%time:~3,2%%time:~6,2%.jpeg
echo %filepath%
hdc_std shell snapshot_display -f %filepath%
hdc_std file recv %filepath% .
