SUMMARY = "Summit Backports for MSD45"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','wb45n','wb45n_nbt',d)}"

RCONFLICTS_${PN} = " \
        kernel-module-60-backports \
        kernel-module-lwb-if-backports \
        kernel-module-ti-backports \
        kernel-module-msd50-backports \
        kernel-module-nx-backports \
        kernel-module-bdsdmac-backports \
        "

module_conf_ath6kl_core  = "options ath6kl_core recovery_enable=1 heart_beat_poll=200"
module_conf_ath6kl_core += "disable_fw_dbglog=1 suspend_mode=1"

KERNEL_MODULE_PROBECONF += "ath6kl_core"

require summit-backports.inc
require radio-stack-msd-version.inc
