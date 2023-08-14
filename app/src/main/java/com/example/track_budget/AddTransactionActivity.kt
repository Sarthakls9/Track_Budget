package com.example.track_budget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        val AddTransactionBtn = findViewById<Button>(R.id.btnAddTransaction)
        val labelInput = findViewById<TextInputEditText>(R.id.labelInput)
        val amountInput = findViewById<TextInputEditText>(R.id.amountInput)
        val descInput = findViewById<TextInputEditText>(R.id.descriptionInput)
        val labelLayout = findViewById<TextInputLayout>(R.id.labelLayout)
        val amountLayout = findViewById<TextInputLayout>(R.id.amountLayout)
        val closeBtn = findViewById<ImageButton>(R.id.IBCloseBtn)

        labelInput.addTextChangedListener {
            if (it!!.count() > 0)
                labelLayout.error = null
        }

        amountInput.addTextChangedListener {
            if (it!!.count() > 0)
                labelLayout.error = null
        }

        AddTransactionBtn.setOnClickListener{
            val label = labelInput.text.toString()
            val description = descInput.text.toString()
            val amount = amountInput.text.toString().toDoubleOrNull()

            if (label.isEmpty())
                labelLayout.error = "Please Enter a Valid Label"
            if (amount == null)
                amountLayout.error = "Please Enter a Valid Amount"
            else {
                val transaction = Transaction(0, label, amount, description)
                insert(transaction)
            }

        }

        closeBtn.setOnClickListener{
            finish()
        }
    }

    private fun insert(transaction: Transaction){
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "transactions").build()
        GlobalScope.launch {
            db.transactionDao().insertAll(transaction)
            finish()
        }
    }
}