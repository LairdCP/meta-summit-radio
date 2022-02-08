SUMMARY = "Laird Connectivity Backports for LWB and LWB5"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-lwb','defconfig-lwb_nbt',d)} \
"

RCONFLICTS_${PN} = " \
        kernel-module-summit-backports-laird \
        kernel-module-sterling-backports-laird \
        kernel-module-lwb5p-backports-laird \
        "

require backports-laird.inc radio-stack-lwb-version.inc
