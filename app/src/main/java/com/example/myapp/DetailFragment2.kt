package com.example.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment2 : Fragment() {
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
        val fragmento: View = inflater.inflate(R.layout.fragment_detail2, container, false)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_new_task2, container, false)
        return fragmento
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var txvNombre: TextView = view.findViewById(R.id.textViewCode)
        var txvApellido: TextView = view.findViewById(R.id.textViewLastName)
        var txvCedula: TextView = view.findViewById(R.id.textViewCode)
        var txvTelefono: TextView = view.findViewById(R.id.textViewPhone)
        var txvDireccion: TextView = view.findViewById(R.id.textViewAddress)
        var id = requireArguments().getInt("id").toString()

        val dbFirebase = FirebaseFirestore.getInstance()
        dbFirebase.collection("ToDo")
            .document(id)
            .get().addOnSuccessListener {
                txvNombre.text = it.get("name") as String
                txvApellido.text = it.get("lastname") as String
                txvCedula.text = it.get("identification") as String
                txvTelefono.text = it.get("phone") as String
                txvDireccion.text = it.get("address") as String
            }
        /*val room: ToDoDatabase = Room.databaseBuilder(
            context?.applicationContext!!,
            ToDoDatabase::class.java,
            "ToDoDatabase"
        )
            .build()
        var toDoDAO: ToDoDAO = room.todoDao()
        runBlocking {
            launch {
                var result = toDoDAO.findById(id)
                txvNombre.text = result.name
                txvApellido.text = result.lastname
                txvCedula.text = result.identification
                txvTelefono.text = result.phone
                txvDireccion.text = result.address
            }
        }*/
    }
            companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment BlankFragment.
             */
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment BlankFragment.
             */

            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                DetailFragment2().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }
        }
