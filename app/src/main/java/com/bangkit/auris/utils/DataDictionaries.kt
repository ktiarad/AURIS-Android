package com.bangkit.auris.utils

import android.content.res.Resources
import android.provider.Settings.System.getString
import com.bangkit.auris.utils.DataDictionary
import com.bangkit.auris.R
import kotlin.coroutines.coroutineContext

object DataDictionaries {

    fun generateDictAlphabet(): List<DataDictionary>{

        val alphabet = ArrayList<DataDictionary>()

        alphabet.add(
            DataDictionary(R.string.letter_A.toString(),
            R.drawable.a)
        )
        alphabet.add(
            DataDictionary(R.string.letter_B.toString(),
            R.drawable.b)
        )
        alphabet.add(
            DataDictionary(R.string.letter_C.toString(),
            R.drawable.c)
        )
        alphabet.add(
            DataDictionary(R.string.letter_D.toString(),
            R.drawable.d)
        )
        alphabet.add(
            DataDictionary(R.string.letter_E.toString(),
            R.drawable.e)
        )
        alphabet.add(
            DataDictionary(R.string.letter_F.toString(),
            R.drawable.f)
        )
        alphabet.add(
            DataDictionary(R.string.letter_G.toString(),
            R.drawable.g)
        )
        alphabet.add(
            DataDictionary(R.string.letter_H.toString(),
            R.drawable.h)
        )
        alphabet.add(
            DataDictionary(R.string.letter_I.toString(),
            R.drawable.i)
        )
        alphabet.add(
            DataDictionary(R.string.letter_J.toString(),
            R.drawable.j)
        )
        alphabet.add(
            DataDictionary(R.string.letter_K.toString(),
            R.drawable.k)
        )
        alphabet.add(
            DataDictionary(R.string.letter_L.toString(),
            R.drawable.l)
        )
        alphabet.add(
            DataDictionary(R.string.letter_M.toString(),
            R.drawable.m)
        )
        alphabet.add(
            DataDictionary(R.string.letter_N.toString(),
            R.drawable.n)
        )
        alphabet.add(
            DataDictionary(R.string.letter_O.toString(),
            R.drawable.o)
        )
        alphabet.add(
            DataDictionary(R.string.letter_P.toString(),
            R.drawable.p)
        )
        alphabet.add(
            DataDictionary(R.string.letter_Q.toString(),
            R.drawable.q)
        )
        alphabet.add(
            DataDictionary(R.string.letter_R.toString(),
            R.drawable.r)
        )
        alphabet.add(
            DataDictionary(R.string.letter_S.toString(),
            R.drawable.s)
        )
        alphabet.add(
            DataDictionary(R.string.letter_T.toString(),
            R.drawable.t)
        )
        alphabet.add(
            DataDictionary(R.string.letter_U.toString(),
            R.drawable.u)
        )
        alphabet.add(
            DataDictionary(R.string.letter_V.toString(),
            R.drawable.v)
        )
        alphabet.add(
            DataDictionary(R.string.letter_W.toString(),
            R.drawable.w)
        )
        alphabet.add(
            DataDictionary(R.string.letter_X.toString(),
            R.drawable.x)
        )
        alphabet.add(
            DataDictionary(R.string.letter_Y.toString(),
            R.drawable.y)
        )
        alphabet.add(
            DataDictionary(R.string.letter_Z.toString(),
            R.drawable.z)
        )

        return alphabet
    }

    fun generateDictNumber(): List<DataDictionary>{
        val number = ArrayList<DataDictionary>()

        number.add(
            DataDictionary(R.string.number_0.toString(),
            R.drawable.number_0)
        )
        number.add(
            DataDictionary(R.string.number_1.toString(),
            R.drawable.number_1)
        )
        number.add(
            DataDictionary(R.string.number_2.toString(),
            R.drawable.number_2)
        )
        number.add(
            DataDictionary(R.string.number_3.toString(),
            R.drawable.number_3)
        )
        number.add(
            DataDictionary(R.string.number_4.toString(),
            R.drawable.number_4)
        )
        number.add(
            DataDictionary(R.string.number_5.toString(),
            R.drawable.number_5)
        )
        number.add(
            DataDictionary(R.string.number_6.toString(),
            R.drawable.number_6)
        )
        number.add(
            DataDictionary(R.string.number_7.toString(),
            R.drawable.number_7)
        )
        number.add(
            DataDictionary(R.string.number_8.toString(),
            R.drawable.number_8)
        )
        number.add(
            DataDictionary(R.string.number_9.toString(),
            R.drawable.number_9)
        )
        number.add(
            DataDictionary(R.string.number_10.toString(),
            R.drawable.number_10)
        )

        return number
    }

    fun generateDictPronoun(): List<DataDictionary> {
        val pronoun = ArrayList<DataDictionary>()

        pronoun.add(
            DataDictionary(R.string.i_word.toString(), R.drawable.gif_pronoun_i)
        )
        pronoun.add(
            DataDictionary(R.string.you.toString(), R.drawable.gif_pronoun_you)
        )
        pronoun.add(
            DataDictionary(R.string.we.toString(), R.drawable.gif_pronoun_we)
        )
//        pronoun.add(
//            DataDictionary(R.string.we2.toString(), R.drawable.a)
//        )
//        pronoun.add(
//            DataDictionary(R.string.youall.toString(), R.drawable.a)
//        )
//        pronoun.add(
//            DataDictionary(R.string.heshe.toString(), R.drawable.a)
//        )
        pronoun.add(
            DataDictionary(R.string.they.toString(), R.drawable.gif_pronoun_they)
        )
//        pronoun.add(
//            DataDictionary(R.string.this_word.toString(), R.drawable.a)
//        )
//        pronoun.add(
//            DataDictionary(R.string.that_word.toString(), R.drawable.a)
//        )

        return pronoun

    }

    fun generateDictInterrogativeWord(): List<DataDictionary> {
        val word = ArrayList<DataDictionary>()

        word.add(
            DataDictionary(R.string.what.toString(), R.drawable.gif_int_what)
        )
        word.add(
            DataDictionary(R.string.who.toString(), R.drawable.gif_int_who)
        )
        word.add(
            DataDictionary(R.string.when_word.toString(), R.drawable.gif_int_when)
        )
        word.add(
            DataDictionary(R.string.howmuch.toString(), R.drawable.gif_int_howmuch)
        )
        word.add(
            DataDictionary(R.string.where.toString(), R.drawable.gif_int_where)
        )
        word.add(
            DataDictionary(R.string.why.toString(), R.drawable.gif_int_why)
        )
        word.add(
            DataDictionary(R.string.how.toString(), R.drawable.gif_int_how)
        )

        return word
    }

    fun generateDictFamily(): List<DataDictionary> {
        val family = ArrayList<DataDictionary>()

//        family.add(
//            DataDictionary(R.string.family.toString(), R.drawable.a)
//        )
        family.add(
            DataDictionary(R.string.father.toString(), R.drawable.gif_fam_father)
        )
        family.add(
            DataDictionary(R.string.mother.toString(), R.drawable.gif_fam_mother)
        )
        family.add(
            DataDictionary(R.string.older.toString(), R.drawable.gif_fam_older)
        )
        family.add(
            DataDictionary(R.string.younger.toString(), R.drawable.gif_fam_younger)
        )
        family.add(
            DataDictionary(R.string.grandfather.toString(), R.drawable.gif_fam_grandfather)
        )
        family.add(
            DataDictionary(R.string.grandmother.toString(), R.drawable.gif_fam_grandmother)
        )
        family.add(
            DataDictionary(R.string.uncle.toString(), R.drawable.gif_fam_uncle)
        )
        family.add(
            DataDictionary(R.string.aunt.toString(), R.drawable.gif_fam_aunt)
        )

        return family
    }

    fun generateDictExpression(): List<DataDictionary> {
        val expression = ArrayList<DataDictionary>()

        expression.add(
            DataDictionary(R.string.happy.toString(), R.drawable.gif_expression_happy)
        )
        expression.add(
            DataDictionary(R.string.cheerful.toString(), R.drawable.gif_expression_cheerful)
        )
        expression.add(
            DataDictionary(R.string.sad.toString(), R.drawable.gif_expression_sad)
        )
        expression.add(
            DataDictionary(R.string.angry.toString(), R.drawable.gif_expression_angry)
        )
        expression.add(
            DataDictionary(R.string.tired.toString(), R.drawable.gif_expression_tired)
        )

        return expression
    }

    fun generateDictDay(): List<DataDictionary> {
        val day = ArrayList<DataDictionary>()

        day.add(
            DataDictionary(R.string.monday.toString(), R.drawable.a)
        )
        day.add(
            DataDictionary(R.string.tuesday.toString(), R.drawable.a)
        )
        day.add(
            DataDictionary(R.string.wednesday.toString(), R.drawable.a)
        )
        day.add(
            DataDictionary(R.string.thursday.toString(), R.drawable.a)
        )
        day.add(
            DataDictionary(R.string.friday.toString(), R.drawable.a)
        )
        day.add(
            DataDictionary(R.string.saturday.toString(), R.drawable.a)
        )
        day.add(
            DataDictionary(R.string.sunday.toString(), R.drawable.a)
        )

        return day
    }

    fun generateDictMonth(): List<DataDictionary> {
        val month = ArrayList<DataDictionary>()

        month.add(
            DataDictionary(R.string.jan.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.feb.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.mar.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.apr.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.may.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.jun.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.jul.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.aug.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.sep.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.oct.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.nov.toString(), R.drawable.a)
        )
        month.add(
            DataDictionary(R.string.dec.toString(), R.drawable.a)
        )


        return month
    }

    fun generateDictVerb(): List<DataDictionary> {
        val verb = ArrayList<DataDictionary>()

        verb.add(
            DataDictionary(R.string.studying.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.reading.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.writing.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.working.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.eating.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.drinking.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.takebath.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.sleeping.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.looking.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.listening.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.talking.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.walking.toString(), R.drawable.a)
        )
        verb.add(
            DataDictionary(R.string.thinking.toString(), R.drawable.a)
        )

        return verb
    }

    fun generateDictNoun(): List<DataDictionary> {
        val noun = ArrayList<DataDictionary>()

        noun.add(
            DataDictionary(R.string.table.toString(), R.drawable.gif_noun_table)
        )
        noun.add(
            DataDictionary(R.string.chair.toString(), R.drawable.gif_noun_chair)
        )
        noun.add(
            DataDictionary(R.string.book.toString(), R.drawable.gif_noun_book)
        )
        noun.add(
            DataDictionary(R.string.pencil.toString(), R.drawable.gif_noun_pencil)
        )
        noun.add(
            DataDictionary(R.string.clothes.toString(), R.drawable.gif_noun_clothes)
        )

        return noun

    }



    fun generateListMenu(): List<Int>{
        val menu = ArrayList<Int>()

        menu.add(R.string.numbers)
        menu.add(R.string.alphabet)
        menu.add(R.string.pronoun)
        menu.add(R.string.interrogative)
        menu.add(R.string.family_title)
        menu.add(R.string.expression)
//        menu.add(R.string.day)
//        menu.add(R.string.month)
//        menu.add(R.string.verb)
        menu.add(R.string.noun)

        return menu

    }

}