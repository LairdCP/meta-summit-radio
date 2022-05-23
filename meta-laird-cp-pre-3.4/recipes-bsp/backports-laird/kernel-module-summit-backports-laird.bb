SUMMARY = "Laird Connectivity Backports for Summit 60"

BACKPORTS_CONFIG = " \
	${@bb.utils.contains('DISTRO_FEATURES','bluetooth','defconfig-summit60','defconfig-summit60_nbt',d)} \
	"

RCONFLICTS_${PN} = " \
        kernel-module-sterling-backports-laird \
        kernel-module-lwb-backports-laird \
        kernel-module-lwb5p-backports-laird \
        "

require backports-laird.inc ../radio-stack-summit-60-version.inc
