#! /bin/sh

dir=$(dirname "${0}")
ver=${1}

# shellcheck disable=SC2034
file="radio-stack-lwb-hashes.inc"

# shellcheck source=/dev/null
. "${dir}/calc-common.sh"

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "lrd-network-manager/src" "summit-network-manager-src-${ver}.tar.xz" "summit-network-manager"
calc_file "backports/laird" "summit-backports-${ver}.tar.bz2" "summit-backports"
calc_file "firmware" "summit-lwbplus-firmware-${ver}.tar.bz2" "lwbplus-firmware"

for i in etsi fcc jp
do
  calc_file "firmware" "summit-lwb-${i}-firmware-${ver}.tar.bz2" "lwb-${i}-firmware"
done

for i in etsi fcc ic jp
do
  calc_file "firmware" "summit-lwb5-${i}-firmware-${ver}.tar.bz2" "lwb5-${i}-firmware"
done

for i in sdio-div sdio-sa sdio-sa-m2 usb-div usb-sa usb-sa-m2
do
  calc_file "firmware" "summit-lwb5plus-${i}-firmware-${ver}.tar.bz2" "lwb5plus-${i}-firmware"
done

for i in sdio pcie
do
  calc_file "firmware" "summit-if573-${i}-firmware-${ver}.tar.bz2" "if573-${i}-firmware"
done

calc_file "firmware" "summit-if513-sdio-firmware-${ver}.tar.bz2" "if513-sdio-firmware"
