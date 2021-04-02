SUMMARY = "Laird Connectivity Sterling 60 Firmware PCIE-USB"

SRC_URI += "${LRD_60_URI_BASE}/laird-${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "5ce115c8c2014a0673a423401001102d"
SRC_URI[sha256sum] = "7f01151a232d2da4a95011187f1682fd05d119cd77483a7e7a1ec2cf7fc4bbe7"

include radio-firmware-60.inc
