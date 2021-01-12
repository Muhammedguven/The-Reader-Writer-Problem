# The-Reader-Writer-Problem
<p>This project is a solution to readers-writers problem in which several processes (readers and writers) are trying to access shared variables. JAVA
Obviously, if two readers access the shared data simultaneously, no adverse effects will result, hence, they are allowed to access. However, if a writer and some other process (either a reader or a writer) access the data simultaneously, chaos may ensue. To ensure that these difficulties do not arise, we require that the writers have exclusive access to the shared data while writing to the data.</p>
<p>• If a writer has begun writing process, then</p>
<p>o No additional writer can perform write function</p>
<p>o No reader is allowed to read</p>
<p>• If 1 or more readers are reading, then </p>
<p>o Other readers may read as well</p>
<p>o No writer may perform write function until all readers have finished reading</p>

<h3> OUTPUT </h3>
<img src= "output.PNG" width="400" height="600">
