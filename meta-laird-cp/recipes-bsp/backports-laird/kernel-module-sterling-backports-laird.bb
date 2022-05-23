SUMMARY = "Laird Connectivity Backports for Sterling 60"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-sterling60','defconfig-sterling60_nbt',d)} \
"

RCONFLICTS:${PN} = " \
        kernel-module-summit-backports-laird \
        kernel-module-lwb-backports-laird \
        kernel-module-lwb5p-backports-laird \
        "

require backports-laird.inc ../radio-stack-sterling-60-version.inc
