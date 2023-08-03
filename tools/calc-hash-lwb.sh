[ -z "${1}" ] && { echo "Usage: $0 <version>"; exit 1; }

ver=${1}

file="radio-stack-lwb-hashes.inc"
prefix="https://files.devops.rfpros.com/builds/linux"
prefixext="https://github.com/LairdCP/Sterling-LWB-and-LWB5-Release-Packages/releases/download/LRD-REL-${ver}"

calc_file () {
  wget ${prefix}/${1}/${ver}/${2} || wget ${prefixext}/${2} || exit 1
  echo "SRC_URI[${3}.md5sum] = \"$(md5sum ${2} | awk '{print $1}')\"" >> ${file}
  echo "SRC_URI[${3}.sha256sum] = \"$(sha256sum ${2} | awk '{print $1}')\"" >> ${file}
  rm -f ${2}
}

echo -e "PV = \"${ver}\"\n" > ${file}

for i in x86 x86_64 arm-eabi arm-eabihf aarch64 powerpc64-e5500
do
  calc_file "summit_supplicant/laird" "summit_supplicant_libs-${i}-${ver}.tar.bz2" "summit-supplicant-libs-${i}"
done

calc_file "summit_supplicant/laird" "summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "lrd-network-manager/src" "lrd-network-manager-src-${ver}.tar.xz" "summit-network-manager"
calc_file "backports/laird" "backports-laird-${ver}.tar.bz2" "summit-backports"
calc_file "firmware" "laird-lwbplus-firmware-${ver}.tar.bz2" "lwbplus-firmware"

for i in etsi fcc jp
do
  calc_file "firmware" "laird-lwb-${i}-firmware-${ver}.tar.bz2" "lwb-${i}-firmware"
done

for i in etsi fcc ic jp
do
  calc_file "firmware" "laird-lwb5-${i}-firmware-${ver}.tar.bz2" "lwb5-${i}-firmware"
done

for i in sdio-div sdio-sa sdio-sa-m2 usb-div usb-sa usb-sa-m2
do
  calc_file "firmware" "laird-lwb5plus-${i}-firmware-${ver}.tar.bz2" "lwb5plus-${i}-firmware"
done
