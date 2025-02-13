issue:

// read zip file
      ZipInputStream zis = new ZipInputStream(new FileInputStream(load));

      List<String> strList = new ArrayList<>();
      byte[] buffer = new byte[1024];

      ZipEntry zipEntry;
      int read;

        while ((zipEntry = zis.getNextEntry()) != null) {
            while ((read = zis.read(buffer)) >= 0) {
              strList.add(new String(buffer,0,read));
            }
        }

        while (zipEntry != null){
          zipEntry = zis.getNextEntry();

        zis.closeEntry();
        zis.close();

        }