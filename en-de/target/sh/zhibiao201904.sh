#!/bin/sh ,# enable environment config,source /etc/profile,source ~/.bash_profile,export ora_db_conn="sqlplus asiainfo/asia123SH@pdb1"
export import_oracle_file="E:\IdeaProjects\en-de\target\sql\zhibiao201904.sql"
$ora_db_conn @$import_oracle_file > ./import_oracle_`date "+%Y%m%d_%H%M%S"`.log
