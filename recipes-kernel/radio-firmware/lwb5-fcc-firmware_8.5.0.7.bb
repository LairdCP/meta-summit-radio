SUMMARY = "Laird Connectivity firmware for the LWB5 SDIO/UART module for operation in the United States"

SRC_URI += "${LRD_LWB_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "025989d9edb571cdc06df5b3c8aa18e9"
SRC_URI[sha256sum] = "221f1a552eb8d273fd0e169da873eb8a9610edc836ae2b026ebd4e7f82f4ffa2"

include radio-firmware-lwb.inc
