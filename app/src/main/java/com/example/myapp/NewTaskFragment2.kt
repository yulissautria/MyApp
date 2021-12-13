package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.myapp.room_database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewTaskFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewTaskFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_task2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val edtName: EditText = view.findViewById(R.id.edtName)
        val edtLastName: EditText = view.findViewById(R.id.edtLastName)
        val edtIdentification: EditText = view.findViewById(R.id.edtIdentification)
        val edtPhone: EditText = view.findViewById(R.id.edtPhone)
        val edtAddress: EditText = view.findViewById(R.id.edtAddress)

       // var tareas = arrayOf("Estudiar", "Mercar", "Hacer ejercicios")
        var tareas: ArrayList<Task> = ArrayList()

        tareas.add(Task(1, "Yulissa", "Utria", "1237440396", "3002146972", "Cartagena"))
        tareas.add(Task(1, "Raul", "Maestre", "184298443", "3049282834", "Manizales"))

        val taskAdapter = ArrayAdapter(context?.applicationContext!!, android.R.layout.simple_spinner_item, tareas)
        taskAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
       // spiTask.adapter = taskAdapter
        val btnNewTask2: Button = view.findViewById(R.id.btnNewTask2)
        btnNewTask2.setOnClickListener {
            // var task = spiTask.selectedItem as Task
            val room: ToDoDatabase = Room.databaseBuilder(
                context?.applicationContext!!,
                ToDoDatabase::class.java,
                "ToDoDatabase"
            )
                .build()
            val taskToDo: Cliente = Cliente(0,
                edtName.text.toString(),
                edtLastName.text.toString(),
                edtIdentification.text.toString(),
                edtPhone.text.toString(),
                edtAddress.text.toString()
            )

            var clienteDAO: ClienteDAO = room.clienteDao()

            val dbFirebase = FirebaseFirestore.getInstance()
            runBlocking {
                launch {
                    var result = clienteDAO.insertTask(taskToDo)
                    if (result != -1L) {
                        dbFirebase.collection("Cliente")
                            .document(result.toString())
                            .set(
                                hashMapOf(
                                    "name" to edtName.text.toString(),
                                    "lastname" to edtLastName.text.toString(),
                                    "identification" to edtIdentification.text.toString(),
                                    "phone" to edtPhone.text.toString(),
                                    "address" to edtAddress.text.toString()
                                )
                            )
                        //var todoDao = room.todoDao()
                        /* runBlocking {
                launch {
                    var result = toDoDAO.insertTask(taskToDo)*/
                        //           Toast.makeText(context?.applicationContext, "" + result, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
           Navigation.findNavController(view).navigate(R.id.nav_todo)
        //  Toast.makeText(context?.applicationContext, task.place, Toast.LENGTH_LONG).show()
         //   Toast.makeText(context?.applicationContext, spiTask.selectedItem.toString(), Toast.LENGTH_LONG).show()

          }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewTaskFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewTaskFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}