# Library Management System - Functional Requirements

## 1. Users & Roles
**User Roles:**
- **Admin**: Manages staff accounts, views analytics and reports.
- **Librarian**: Manages books, handles lending and returns, manages fines.
- **Member**: Searches books, views availability, locates books in library.
- **Guest**: No access to search or library functions.

**Access Control:**
- Admin has full privileges.
- Librarian has restricted management privileges (no access to admin settings).
- Members can only view and borrow books as per rules.
- Guests have no functional access.

**Account Creation:**
- Only staff can create accounts for members.
- Staff accounts are created by Admin.
- No self-registration.

**Staff Borrowing Rules:**
- Staff accounts can also have member privileges for borrowing (frontend-controlled).

---

## 2. Book Lending Rules
- **Checkout Eligibility:** Only staff can perform checkout on behalf of members.
- **Self-checkout:** Not supported.
- **Borrowing Limit:** Max **5 books** per member.
- **Loan Period:** 14 days (same for all users).
- **Renewals:** 1 renewal allowed per book.
- **Late Returns:**
  - Fine: Rs.5/day until returned.
  - Borrowing blocked until fine is cleared.
- **Reservations:** Supported.
  - If reserved, the member gets priority when returned.
  - Reservation notification sent to member.

---

## 3. Inventory Management
- System tracks **multiple copies** of the same book as individual records (each copy has unique ID).
- Tracks condition of each copy (Good, Damaged, Lost).
- Damaged/lost books require manual action by librarian.

---

## 4. Returns
- Only the **original borrower** can return a book.
- Librarian inspects books upon return and updates condition.

---

## 5. Notifications
- **Due Date Reminders:** Sent 2 days before due date.
- **Overdue Alerts:** Sent daily until returned.
- **Reservation Ready:** Sent when reserved book is returned and available.

---

## 6. Reports & Analytics
System generates analytics on:
- Most borrowed books.
- Overdue counts.
- Active members.
- Inventory changes.

---

## 7. Access & Availability
- Web-based application (initial version).
- Accessible from both inside and outside library network (requires authentication).

---

## 8. Additional Business Rules
- Fine accumulation has no upper cap.
- Borrowing suspended if outstanding fines exist.
- Reservation queue maintained if multiple members reserve same book.

---

## 9. Edge Case Handling
- **Lost Books:** Marked as lost, borrowing suspended until resolved.
- **Damaged Books:** Status updated, may incur replacement cost.
- **Multiple Reservations:** Handled in FIFO order.
"""


