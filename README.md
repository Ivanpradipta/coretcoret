## (13)Introduction React

### 1. ReactJS
ReactJS adalah library JavaScript yang digunakan untuk membangun tampilan pada website ataupun mobile. React sendiri memiliki sejarah yang lumayan panjang, awalnya rReact ini diciptakan oleh Jordan Walke pada tahun 2011 karena pada saat itu Facebook mengalami masalah untuk pengembangan aplikasi mereka maka dari itu diciptakanlah ReactJs ini sebagai suatu pemecahan masalah

### 2. Kelebihan ReactJS dan VDOM
- Declarative adalah salah satu cara pendekatan yang berfokus pada apa yang ingin dicapai oleh si penulis code 
- Component-Based adalah ketika kita membuat suatu aplikasi yang kompleks kita bisa membreakdown menjadi bagian-bagian yang kecil yang mempermudah kita untuk membuat code  
- Learn Once, Write Anywhere maksudnya adalah ketika kita sudah paham React dan bisa membuat aplikasi membuat React kita bisa mudah dalam membuat aplikasi native hanya perlu penyesuaian sedikit
#### VDOM
VDOM adalah representasi dari UI yang berbentuk Javascript object yang disimpan di memory. pada ReactJS sendiri penyimpanan dan sinkronisasi virtual DOM terhadap DOM dilakukan oleh rendering React DOM. cara React melakukan render kekita ada suatu state pada komponen yang beruah adalah Compute Diff jadi ada dimana saja perubhaanya setelah didapat maka akan dilakukan re-render. 

### 3. Cara Pembuatan Project pada React
- Pertama kita bisa menggunakan CRA untuk membuat Project pada React di dalam cmd dengan menuliskan 
```bash
      npx create-react-app nama-project
   ```
- untuk melakukan run pada browser kita bisa menggunakan npm
```bash
      npm start
   ```
