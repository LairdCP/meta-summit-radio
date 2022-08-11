SUMMARY = "Laird Connectivity Backports for MSD45"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-wb45n','defconfig-wb45n_nbt',d)} \
	"

RCONFLICTS_${PN} = " \
        kernel-module-summit-backports-laird \
        kernel-module-sterling-backports-laird \
        kernel-module-lwb-backports-laird \
        kernel-module-lwb5p-backports-laird \
        "

require backports-laird.inc radio-stack-4550-version.inc
