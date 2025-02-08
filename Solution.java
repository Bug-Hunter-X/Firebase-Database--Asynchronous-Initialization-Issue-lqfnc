Instead of directly accessing the database in `onCreate()`, use a listener to ensure the database is ready:

```java
// ... Firebase initialization ...

DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();

databaseRef.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        // Access the database here. The database is guaranteed to be initialized.
        // ... your database operations ...
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        // Handle errors
    }
});
```
Alternatively, you can use a task to check for database connectivity:
```java
DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
databaseRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
    @Override
    public void onComplete(@NonNull Task<DataSnapshot> task) {
        if (task.isSuccessful()) {
            DataSnapshot dataSnapshot = task.getResult();
            // Access data here
        } else {
            // Handle failures
        }
    }
});
```