package iesb.com.customcomponents.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import iesb.com.customcomponents.R
import iesb.com.customcomponents.components.OnBoarding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addOnBoarding()
    }

    private fun addOnBoarding() {
        val set = ConstraintSet() //Cria um conjunto de Constraints

        val onBoarding = OnBoarding(this) //Criar o componente
        mainActivityLayout.addView(onBoarding, 0) //Indice 0 é o indice mais a frente na hierarquia de Views

        set.clone(mainActivityLayout) //Pega as Constraints da existentes e adiciona mais algumas

        //Conecta o top do onBoarding ao top do mainActivityLayout com margem 0
        set.connect(onBoarding.id, ConstraintSet.TOP, mainActivityLayout.id, ConstraintSet.TOP, 0)
        set.connect(onBoarding.id, ConstraintSet.START, mainActivityLayout.id, ConstraintSet.START, 0)
        set.connect(onBoarding.id, ConstraintSet.END, mainActivityLayout.id, ConstraintSet.END, 0)
        set.connect(onBoarding.id, ConstraintSet.BOTTOM, mainActivityLayout.id, ConstraintSet.BOTTOM, 0)

        //Aplica as contraints no mainActivityLayout
        set.applyTo(mainActivityLayout)

    }
}
