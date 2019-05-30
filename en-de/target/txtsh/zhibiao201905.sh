export converformat=$(cat E:\IdeaProjects\en-de\target\txt\zhibiao201905.txt | sed 's/[ ][ ]*/,/g' > E:\IdeaProjects\en-de\target\txt\zhibiao201905.csv)
$converformat > ./result_converformat_`date "+%Y%m%d_%H%M%S"`.log
