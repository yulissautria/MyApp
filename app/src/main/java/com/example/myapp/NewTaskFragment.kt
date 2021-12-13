package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.myapp.room_database.ToDo
import com.example.myapp.room_database.ToDoDAO
import com.example.myapp.room_database.ToDoDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewTaskFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val edtCode: EditText = view.findViewById(R.id.edtCode)
        val edtSize: EditText = view.findViewById(R.id.edtSize)
        val edtColor: EditText = view.findViewById(R.id.edtColor)
        val edtType: EditText = view.findViewById(R.id.edtType)
        val spiTask: Spinner = view.findViewById(R.id.edtType)
        val edtPrice: EditText = view.findViewById(R.id.edtPrice)
        // var tareas = arrayOf("Estudiar", "Mercar", "Hacer ejercicios")
        var tareas: ArrayList<Task> = ArrayList()
        tareas.add(Task(1, "1234", "39", "Negro", "Tacón Delgado", "50.000"))
        tareas.add(Task(2, "0123", "37", "Marron", "Tacón Grueso", "35.000"))
        tareas.add(Task(3, "12345", "35", "Blanco", "Tacón de Plataforma", "40.000"))

        val taskAdapter = ArrayAdapter(
            context?.applicationContext!!,
            android.R.layout.simple_spinner_item,
            tareas
        )
        taskAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiTask.adapter = taskAdapter
        val btnNewTask: Button = view.findViewById(R.id.btnNewTask)


        btnNewTask.setOnClickListener {
            // var task = spiTask.selectedItem as Task
            val room: ToDoDatabase = Room.databaseBuilder(
                context?.applicationContext!!,
                ToDoDatabase::class.java,
                "ToDoDatabase"
            )
                .build()
            val taskToDo: ToDo = ToDo(
                0, edtCode.text.toString(), edtSize.text.toString(),
                edtColor.text.toString(), edtType.text.toString(), edtPrice.text.toString())
            var toDoDAO: ToDoDAO = room.todoDao()


            //var todoDao = room.todoDao()
            val dbFirebase = FirebaseFirestore.getInstance()
            runBlocking {
                launch {
                    var result = toDoDAO.insertTask(taskToDo)
                    if (result != -1L) {
                        dbFirebase.collection("Producto")
                            .document(result.toString())
                            .set(
                                hashMapOf(
                                    "code" to edtCode.text.toString(),
                                    "size" to edtSize.text.toString(),
                                    "color" to edtColor.text.toString(),
                                    "type" to edtType.text.toString(),
                                    "price" to edtPrice.text.toString()
                                )
                            )

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
         * @return A new instance of fragment NewTaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
