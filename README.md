# GithubApp

# Kriteria:
**Fitur yang harus ada pada aplikasi:**

  **1. Search User**
     
     Syarat:
      - Menampilkan list data user dari API menggunakan RecyclerView dengan data minimal foto avatar dan username.
      - Pencarian user menggunakan data dari API berjalan dengan baik.
      - Pengguna dapat melihat halaman detail dari hasil daftar pencarian.

  **2. Detail User**
     
     Syarat:
      - Terdapat informasi detail dari seorang user. Berikut beberapa informasi yang wajib ditampilkan pada halaman aplikasi. 
         - Foto Avatar
         - Username
         - Nama
         - Jumlah Followers
         - Jumlah Following
      - Catatan: Pastikan kembali semua informasi tersebut ada.
      - Menampilkan fragment List Follower & List Following yang diambil dari API.
      - Menggunakan Tab Layout dan ViewPager sebagai navigasi antara halaman List Follower dan List Following.

**3. Terdapat indikator loading saat aplikasi memuat data di semua bagian yang mengambil data dari API, yaitu**

      - List data user
      - Detail user
      - List following
      - List follower

**4. Favorite User dengan Database**

      - Aplikasi harus bisa menambah dan menghapus user dari daftar favorite.
      - Aplikasi harus mempunyai halaman yang menampilkan daftar favorite.
      - Menampilkan halaman detail dari daftar favorite.

**5. Pengaturan Tema**

      - Membuat menu untuk mengganti tema (light theme atau dark theme) dengan menggunakan penyimpanan key-value.
      - Pastikan tema tetap terimplementasi walaupun aplikasi ditutup dan dibuka kembali.
         - Caranya yaitu dengan meng-observe data dan mengimplementasikan tema pada halaman pertama.
      - Pastikan setiap komponen dan indikator tetap terlihat jelas ketika berubah tema (Jangan ketika tema gelap, teks dan indikator juga berwarna hitam)

# Penilaian:
**1. Menerapkan tampilan aplikasi yang sesuai standar:**

      - Tampilan aplikasi memiliki width, height, margin, dan padding yang sesuai.
      - Pemilihan warna yang sesuai tema aplikasi. 
      - Tidak ada komponen yang saling bertumpuk.
      - Penggunaan komponen yang sesuai dengan fungsinya.
        Contoh : Komponen ImageView yang dijadikan sebagai button navigasi.
      - Menggunakan SearchView pada fitur pencarian.
      - Aplikasi bisa memberikan pesan eror jika data tidak berhasil ditampilkan.

**2. Menuliskan kode dengan bersih.**

      - Bersihkan comment dan kode yang tidak digunakan.
      - Indentasi yang sesuai.
      - Menghapus import yang tidak digunakan.

**3. Aplikasi bisa menjaga data yang sudah dimuat ketika terjadi pergantian orientasi dari potrait ke landscape atau sebaliknya.**

**4. Mengasah penggunaan library networking selain LoopJ (seperti Retrofit, Fast Android Networking, dsb).**

**5. Menerapkan Android Architecture Component (minimal ViewModel dan LiveData) dengan benar.**

      - Tidak membuat satu ViewModel untuk beberapa view sekaligus, tetapi dipisah.
      
**6. Menggunakan DataStore untuk penyimpanan pengaturan.**

**7. Menambahkan testing (unit test data ui test) minimal satu test case.**
