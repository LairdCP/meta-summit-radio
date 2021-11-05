SUMMARY = "Laird Connectivity Backports for LWB5+"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-lwb5p','defconfig-lwb5p_nbt',d)} \
"

RCONFLICTS_${PN} = " \
        kernel-module-summit-backports-laird \
        kernel-module-sterling-backports-laird \
        kernel-module-lwb-backports-laird \
        "

require backports-laird.inc radio-stack-lwb-version.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
