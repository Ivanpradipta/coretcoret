## (13) Inroduction ReactJS :open_book: 	

### 1. Apa itu React JS :question:
ReactJS adalah library JavaScript yang digunakan untuk membangun User Interface (UI) suatu halaman pada website ataupun mobile. ReactJS merupakan open source yang dikelola oleh Facebook. Pada arsitekture MVC atau Model, View, and Controller, ReactJS hanya mengambil peran pada bagian View saja. Seperti jenisnya, ReactJS sebagai library Javascript memiliki fungsi untuk membuat proses pengkodean Javascript menjadi lebih efisien dan singkat. Programmer hanya perlu membuat suatu komponen dan komponen tersebut dapat digunakan kapan saja dan berapa kalipun sesuai konteks dari komponen tersebut. Sehingga hal inilah yang dapat mempersingkat penulisan kode.

#### Apa yang menjadi kelebihan dari ReactJS :interrobang:
   - **Declarative**
      Artinya penulisan kode dirancang lebih dapat dibaca berdasarkan aliran logika dan lebih mudah didebug.
   - **Component-Based**
      Artinya pada penulisan code Javascript dapat dilakukan dengan suatu bagian code yang lebih kecil / komponen dan nantinya komponen tersebut memiliki peran masing-masing, komponen itu dapat di panggil untuk dirender untuk tampil pada web kapan saja dan dapat dipanggil berapa kalipun sesuai kebutuhan.
   - **Larn Once, Write Anywhere**
      Artinya ReactJS juga dapat merender di server Website menggunakan Node dan menjalankan aplikasi mobbile menggunakan React Native.

### 2. Virtual DOM pada ReactJS
Virtual DOM berguna untuk melihat bagian dari DOM asli. Ketika akan mengupdate DOM menggunakan JSX (Javascript XML), ReactJS akan membuat salinan DOM aslinya yang disebut dengan Virtual DOM. Jadi, ketika ada bagian pada DOM yang perlu diubah, maka ReactJS akan mengubah bagian tersebut saja dan tidak akan me-refresh tampilan.

### 3. Membuat Project React :open_file_folder:
Ada beberapa hatoolsl yang perlu disiapkan untuk memulai project React, yaitu:
   - Web Browser, seperti Chrome atau Edge
   - Code Editor, seperti VSCode atau Sublime Text.
   - Command Line, seperti CMD, Git, atau Powershell.
   - Menginstall NodeJS (Versi LTS direkomendasikan) [Link](https://nodejs.org/en/)

#### Membuat Aplikasi/Project dengan React
   - **CRA (create-react-app)**
      Caranya dengan memposisikan Command Line pada folder yang ingin digunakan sebagai   project React. Lalu masukan command `cra` seperti berikut:
   ```bash
      // template
      npx create-react-app [project-name]

      // example
      npx create-react-app my-app
   ```

   - **Vite**
      Caranya dengan memposisikan Command Line pada folder yang ingin digunakan sebagai project React. Lalu masukan command `Vite` seperti berikut:

   ```bash
      // jika menggunakan npm
      npm create vite@latest my-react-app -- --template react

      // jika menggunakan yarn
      yarn create vite my-react-app --template react
   ```

### Referensi
- [reactjs.org](https://reactjs.org/)
- [Vite](https://vitejs.dev/guide/)
