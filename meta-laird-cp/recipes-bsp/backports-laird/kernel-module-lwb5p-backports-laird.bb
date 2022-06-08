SUMMARY = "Laird Connectivity Backports for LWB5+"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-lwb5p','defconfig-lwb5p_nbt',d)} \
"

RCONFLICTS:${PN} = " \
        kernel-module-summit-backports-laird \
        kernel-module-sterling-backports-laird \
        kernel-module-lwb-backports-laird \
        "

require backports-laird.inc radio-stack-lwb-version.inc
