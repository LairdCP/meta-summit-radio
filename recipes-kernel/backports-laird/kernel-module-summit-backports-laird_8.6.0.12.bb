SUMMARY = "Laird Connectivity Backports for Summit 60"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-summit60','defconfig-summit60_nbt',d)} \
"

SRC_URI += "${LRD_60_URI_BASE}/${BACKPORTS_FILE}"

SRC_URI[md5sum] = "579e20e71bd5a46359ea7c046bdf1f81"
SRC_URI[sha256sum] = "62c257d980db6f643a76d4156afd1967ad2c5ca5c2737c25be66e58b489c3cdd"

RCONFLICTS_${PN} = " \
        kernel-module-sterling-backports-laird \
        kernel-module-lwb-backports-laird \
        kernel-module-lwb5p-backports-laird \
        "

include backports-laird.inc
