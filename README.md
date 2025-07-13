<h1>Theater Booking System – Java RMI</h1>

<h2>Overview</h2>
<p>
<b>Theater Booking System</b> is a distributed application that allows clients to reserve, cancel, and view seats for a theater performance via Java RMI (Remote Method Invocation).  
It supports concurrent clients, maintains data consistency, and provides notifications when seats become available.
</p>


<hr>

<h2>Features</h2>
<ul>
<li>✅ Supports multiple seat zones with independent pricing and availability.</li>
<li>✅ Clients can book and cancel seats remotely.</li>
<li>✅ Clients can view current reservations and guest lists.</li>
<li>✅ Notification mechanism: clients can subscribe for alerts when seats become available.</li>
<li>✅ Handles concurrent clients gracefully via Java RMI.</li>
</ul>

<hr>

<h2>Architecture</h2>
<p>
The application follows a <b>Client-Server</b> model using Java RMI.
</p>

<ul>
<li><b>THClient.java</b>: Command-line client interface for issuing booking/cancellation/list requests.</li>
<li><b>THServer.java</b>: Starts the server, exports the implementation, and binds it to the RMI registry.</li>
<li><b>THImpl.java</b>: Implements the core business logic for reservations and notifications.</li>
<li><b>SeatType.java</b>: Represents a seat category with price, availability, guest list, and subscriber list.</li>
<li><b>THInterface.java</b>: Declares the remote methods available to clients.</li>
</ul>

<hr>

<h2>Available Commands</h2>
<ul>
<li><code>java THClient list &lt;hostname&gt;</code>: Lists all seat zones and their current availability and price.</li>
<li><code>java THClient book &lt;type&gt; &lt;number&gt; &lt;name&gt; &lt;hostname&gt;</code>: Books a number of seats of the specified type for the given guest.</li>
<li><code>java THClient cancel &lt;type&gt; &lt;number&gt; &lt;name&gt; &lt;hostname&gt;</code>: Cancels reservations for the guest in the specified zone.</li>
<li><code>java THClient guests &lt;hostname&gt;</code>: Displays all guests and their reservations.</li>
</ul>

<hr>

<h2>Seat Zones</h2>
<table>
<tr><th>Code</th><th>Description</th><th>Seats</th><th>Price (€)</th></tr>
<tr><td>ΠΑ</td><td>Πλατεία - Ζώνη Α</td><td>100</td><td>50</td></tr>
<tr><td>ΠΒ</td><td>Πλατεία - Ζώνη Β</td><td>200</td><td>40</td></tr>
<tr><td>ΠΓ</td><td>Πλατεία - Ζώνη Γ</td><td>300</td><td>30</td></tr>
<tr><td>ΚΕ</td><td>Κεντρικός Εξώστης</td><td>250</td><td>35</td></tr>
<tr><td>ΠΘ</td><td>Πλαϊνά Θεωρεία</td><td>50</td><td>25</td></tr>
</table>

<hr>

<h2>Build & Run</h2>

<h3>Compile</h3>
<pre><code>javac *.java</code></pre>

<h3>Start RMI Registry</h3>
<pre><code>rmiregistry</code></pre>

<h3>Start the Server</h3>
<pre><code>java THServer</code></pre>

<h3>Run Client Commands</h3>
<pre><code>java THClient list localhost
java THClient book ΠΑ 2 Maria localhost
java THClient cancel ΠΑ 1 Maria localhost
java THClient guests localhost
</code></pre>

<hr>

<h2>Sample Output</h2>
<pre>
$ java THClient list localhost
100 θέσεις Πλατεία - Ζώνη Α (κωδικός: ΠΑ) - τιμή: 50 Ευρώ
200 θέσεις Πλατεία - Ζώνη Β (κωδικός: ΠΒ) - τιμή: 40 Ευρώ
...

$ java THClient book ΠΑ 2 Maria localhost
success: 100 euros

$ java THClient cancel ΠΑ 1 Maria localhost
success: 1 reservations left

$ java THClient guests localhost
total guests: 1
Maria (1)
</pre>

<hr>

<h2>Notification Feature</h2>
<ul>
<li>If a booking fails due to no availability, the client is asked if they wish to subscribe to notifications.</li>
<li>When a cancellation frees up seats, subscribed clients are notified on their next interaction.</li>
</ul>

<hr>
